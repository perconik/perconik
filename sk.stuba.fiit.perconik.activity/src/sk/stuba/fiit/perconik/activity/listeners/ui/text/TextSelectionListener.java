package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.selection.TextSelectionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextSelectionListener.Action.SELECT;
import static sk.stuba.fiit.perconik.activity.serializers.ui.Ui.dereferencePart;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.6.alpha")
public final class TextSelectionListener extends AbstractTextListener implements PartListener, sk.stuba.fiit.perconik.core.listeners.TextSelectionListener {
  // TODO fails on shutdown if both this and text view listener are processing pending events

  // TODO note that select must be initiated by user after startup to be sent on shutdown

  static final TimeValue selectionEventPause = of(500L, MILLISECONDS);

  static final TimeValue selectionEventWindow = of(10L, SECONDS);

  private final TextSelectionEvents events;

  public TextSelectionListener() {
    this.events = new TextSelectionEvents(this);
  }

  enum Action implements ActivityListener.Action {
    SELECT;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "text", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  Event build(final long time, final Action action, final LinkedList<TextSelectionEvent> sequence, final IWorkbenchPart part, final ITextSelection selection, final LineRegion region) {
    assert sequence.getLast().selection.equals(selection);

    Event data = this.build(time, action, part, region);

    data.put(key("sequence", "first", "timestamp"), sequence.getFirst().time);
    data.put(key("sequence", "first", "raw"), new TextSelectionSerializer().serialize(sequence.getFirst().selection));

    data.put(key("sequence", "last", "timestamp"), sequence.getLast().time);
    data.put(key("sequence", "last", "raw"), new TextSelectionSerializer().serialize(sequence.getLast().selection));

    data.put(key("sequence", "count"), sequence.size());

    return data;
  }

  void process(final long time, final Action action, final LinkedList<TextSelectionEvent> sequence, final IWorkbenchPart part, final ITextSelection selection) {
    IDocument document = Parts.getDocument(TextViewerSupport.getTextViewer(part));

    LineRegion region = LineRegion.compute(document, selection.getOffset(), selection.getLength(), selection.getText()).normalize();

    this.send(action.getPath(), this.build(time, action, sequence, part, selection, region));
  }

  static final class TextSelectionEvents extends ContinuousEvent<TextSelectionListener, TextSelectionEvent> {
    TextSelectionEvents(final TextSelectionListener listener) {
      super(listener, "selection", selectionEventPause, selectionEventWindow);
    }

    @Override
    protected boolean accept(final LinkedList<TextSelectionEvent> sequence, final TextSelectionEvent event) {
      IDocument document = Parts.getDocument(TextViewerSupport.getTextViewer(event.part));

      if (document == null) {
        if (this.log.isEnabled()) {
          this.log.print("%s: document not found for %s -> ignore", "selection", event.part);
        }

        return false;
      }

      boolean empty = event.isSelectionTextEmpty();

      if (sequence.isEmpty()) {
        return !empty;
      }

      TextSelectionEvent last = sequence.getLast();

      if (empty && last.part != event.part) {
        return false;
      }

      if (last.contentEquals(event)) {
        return false;
      }

      return true;
    }

    @Override
    protected boolean continuous(final LinkedList<TextSelectionEvent> sequence, final TextSelectionEvent event) {
      return sequence.getLast().isContinuousWith(event);
    }

    @Override
    protected void process(final LinkedList<TextSelectionEvent> sequence) {
      this.listener.handleSelectionEvents(sequence);
    }
  }

  void handleSelectionEvents(final LinkedList<TextSelectionEvent> sequence) {
    this.execute(new Runnable() {
      public void run() {
        TextSelectionEvent event = sequence.getLast();

        process(event.time, SELECT, sequence, event.part, event.selection);
      }
    });
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partClosed(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partActivated(final IWorkbenchPartReference reference) {
    // ensures that a selection change is always generated on part activation,
    // this primarily helps handling selection context switch in some cases,
    // as a side effect it breaks event continuation

    this.selectionChanged(reference);
  }

  public void partDeactivated(final IWorkbenchPartReference reference) {
    // ensures that pending events are flushed on part deactivation,
    // this primarily handles proper selection termination on shutdown,
    // as a side effect it breaks event continuation

    this.events.flush();
  }

  public void partVisible(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partHidden(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partBroughtToTop(final IWorkbenchPartReference reference) {
    // ignore
  }

  public void partInputChanged(final IWorkbenchPartReference reference) {
    // ignore
  }

  private void push(final long time, final IWorkbenchPart part, final ITextSelection selection) {
    // eagerly filter out empty selections, no need to push event
    // only to be filtered during acceptance phase by such simple operation

    if (selection.isEmpty()) {
      if (this.log.isEnabled()) {
        this.log.print("%s: selection empty -> ignore", "selection");
      }

      return;
    }

    this.events.push(new TextSelectionEvent(time, part, selection));
  }

  public void selectionChanged(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    IWorkbenchPart part = dereferencePart(reference);
    ITextViewer viewer = TextViewerSupport.getTextViewer(part);

    if (viewer == null) {
      if (this.log.isEnabled()) {
        this.log.print("%s: viewer not found for %s -> ignore", "selection", part);
      }

      return;
    }

    ISelection selection = viewer.getSelectionProvider().getSelection();

    if (!(selection instanceof ITextSelection)) {
      if (this.log.isEnabled()) {
        this.log.print("%s: selection not textual in %s of %s -> ignore", "selection", viewer, part);
      }

      return;
    }

    this.push(time, part, (ITextSelection) selection);
  }

  public void selectionChanged(final IWorkbenchPart part, final ITextSelection selection) {
    final long time = this.currentTime();

    this.push(time, part, selection);
  }
}
