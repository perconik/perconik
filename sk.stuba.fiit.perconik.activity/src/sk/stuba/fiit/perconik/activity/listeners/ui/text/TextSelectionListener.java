package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.selection.TextSelectionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.PRE_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextSelectionListener.Action.SELECT;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.2.alpha")
public final class TextSelectionListener extends AbstractTextListener implements sk.stuba.fiit.perconik.core.listeners.TextSelectionListener {
  static final TimeValue selectionEventPause = of(500, MILLISECONDS);

  static final TimeValue selectionEventWindow = of(10, SECONDS);

  private final TextSelectionEvents events;

  public TextSelectionListener() {
    this.events = new TextSelectionEvents(this);

    PRE_UNREGISTER.add(this, new NamedRunnable(this.getClass(), "UnsentSelectionHandler") {
      public void run() {
        handleUnsentSelectionOnUnregistration();
      }
    });
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

  Event build(final long time, final Action action, final IEditorPart editor, final UnderlyingView<?> view, final LineRegion region, final ITextSelection selection) {
    Event data = this.build(time, action, editor, view, region);

    data.put(key("selection"), new TextSelectionSerializer().serialize(selection));

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchPart part, final ITextSelection selection) {
    if (!(part instanceof IEditorPart)) {
      return;
    }

    IEditorPart editor = (IEditorPart) part;

    UnderlyingView<?> view = UnderlyingView.from(editor);

    if (view == null) {
      return;
    }

    LineRegion region = LineRegion.compute(view.getDocument(), selection.getOffset(), selection.getLength(), selection.getText());

    this.send(action.getPath(), this.build(time, action, editor, view, region, selection));
  }

  static final class TextSelectionEvents extends ContinuousEvent<TextSelectionListener, TextSelectionEvent> {
    TextSelectionEvents(final TextSelectionListener listener) {
      super(listener, "selection", selectionEventPause, selectionEventWindow);
    }

    @Override
    protected boolean accept(final LinkedList<TextSelectionEvent> sequence, final TextSelectionEvent event) {
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
      this.listener.handleAcceptedSelection(sequence.getLast());
    }
  }

  void handleAcceptedSelection(final TextSelectionEvent event) {
    execute(new Runnable() {
      public void run() {
        process(event.time, SELECT, event.part, event.selection);
      }
    });
  }

  void handleUnsentSelectionOnUnregistration() {
    this.events.flush();
  }

  public void selectionChanged(final IWorkbenchPart part, final ITextSelection selection) {
    final long time = this.currentTime();

    if (selection.isEmpty()) {
      return;
    }

    this.events.push(new TextSelectionEvent(time, part, selection));
  }
}
