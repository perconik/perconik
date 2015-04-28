package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.selection.MarkSelectionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.MarkSelectionListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextMarkListener.Action.MARK;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.4.alpha")
public final class TextMarkListener extends AbstractTextListener implements MarkSelectionListener, PartListener {
  // TODO note that mark selection is generated only on incremental search (ctrl + j)
  //      or for marked regions used in Emacs style editing

  static final TimeValue selectionEventPause = of(500L, MILLISECONDS);

  static final TimeValue selectionEventWindow = of(10L, SECONDS);

  private final TextMarkEvents events;

  public TextMarkListener() {
    this.events = new TextMarkEvents(this);
  }

  enum Action implements ActivityListener.Action {
    MARK;

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

  Event build(final long time, final Action action, final LinkedList<TextMarkEvent> sequence, final IWorkbenchPart part, final IMarkSelection selection, final LineRegion region) {
    assert sequence.getLast().selection.equals(selection);

    Event data = this.build(time, action, part, region);

    data.put(key("sequence", "first", "timestamp"), sequence.getFirst().time);
    data.put(key("sequence", "first", "raw"), new MarkSelectionSerializer(TREE).serialize(sequence.getFirst().selection));

    data.put(key("sequence", "last", "timestamp"), sequence.getLast().time);
    data.put(key("sequence", "last", "raw"), new MarkSelectionSerializer(TREE).serialize(sequence.getLast().selection));

    data.put(key("sequence", "count"), sequence.size());

    return data;
  }

  void process(final long time, final Action action, final LinkedList<TextMarkEvent> sequence, final IWorkbenchPart part, final IMarkSelection selection) {
    IDocument document = selection.getDocument();

    LineRegion region = LineRegion.compute(document, selection.getOffset(), selection.getLength()).normalize();

    this.send(action.getPath(), this.build(time, action, sequence, part, selection, region));
  }

  static final class TextMarkEvents extends ContinuousEvent<TextMarkListener, TextMarkEvent> {
    TextMarkEvents(final TextMarkListener listener) {
      super(listener, "text-mark", selectionEventPause, selectionEventWindow);
    }

    @Override
    protected boolean accept(final LinkedList<TextMarkEvent> sequence, final TextMarkEvent event) {
      return true;
    }

    @Override
    protected boolean continuous(final LinkedList<TextMarkEvent> sequence, final TextMarkEvent event) {
      return sequence.getLast().isContinuousWith(event);
    }

    @Override
    protected void process(final LinkedList<TextMarkEvent> sequence) {
      this.listener.handleSelectionEvents(sequence);
    }
  }

  void handleSelectionEvents(final LinkedList<TextMarkEvent> sequence) {
    this.execute(new Runnable() {
      public void run() {
        TextMarkEvent event = sequence.getLast();

        process(event.time, MARK, sequence, event.part, event.selection);
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
    // ignore
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

  private void push(final long time, final IWorkbenchPart part, final IMarkSelection selection) {
    this.events.push(new TextMarkEvent(time, part, selection));
  }

  public void selectionChanged(final IWorkbenchPart part, final IMarkSelection selection) {
    final long time = this.currentTime();

    this.push(time, part, selection);
  }
}
