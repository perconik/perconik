package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.text.LineRegionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.ViewportListener;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.POST_REGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextViewListener.Action.VIEW;
import static sk.stuba.fiit.perconik.activity.serializers.ui.Ui.dereferenceEditor;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.ui.Editors.waitForActiveEditorReference;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.3.alpha")
public final class TextViewListener extends AbstractTextListener implements EditorListener, ViewportListener {
  // TODO note that event generated while workbench.isClosing do not have editor.viewer field since the viewer is already disposed

  static final TimeValue viewEventPause = of(250, MILLISECONDS);

  static final TimeValue viewEventWindow = of(1000, MILLISECONDS);

  private final TextViewEvents events;

  public TextViewListener() {
    this.events = new TextViewEvents(this);

    // ensures that a user-to-editor view is initiated on active editor
    // right after the workbench starts, i.e. this listener is registered

    POST_REGISTER.add(this, new NamedRunnable(this.getClass(), "ActiveEditorViewEventGenerator") {
      public void run() {
        generateActiveEditorViewEvent();
      }
    });
  }

  enum Action implements ActivityListener.Action {
    VIEW;

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

  Event build(final long time, final Action action, final LinkedList<TextViewEvent> sequence, final IEditorPart editor, final LineRegion region) {
    Event data = super.build(time, action, editor);

    data.put(key("view", "events", "first", "timestamp"), sequence.getFirst().time);
    data.put(key("view", "events", "first", "raw"), new LineRegionSerializer().serialize(sequence.getFirst().region));

    data.put(key("view", "events", "last", "timestamp"), sequence.getLast().time);
    data.put(key("view", "events", "last", "raw"), new LineRegionSerializer().serialize(sequence.getLast().region));

    data.put(key("view", "events", "count"), sequence.size());

    data.put(key("view", "region"), new LineRegionSerializer().serialize(region));

    return data;
  }

  void process(final long time, final Action action, final LinkedList<TextViewEvent> sequence, final ITextViewer viewer) {
    IEditorPart editor = Editors.forTextViewer(viewer);

    LineRegion region = sequence.getLast().region;

    this.send(action.getPath(), this.build(time, action, sequence, editor, region));
  }

  static final class TextViewEvents extends ContinuousEvent<TextViewListener, TextViewEvent> {
    protected TextViewEvents(final TextViewListener listener) {
      super(listener, "view", viewEventPause, viewEventWindow);
    }

    @Override
    protected boolean accept(final LinkedList<TextViewEvent> sequence, final TextViewEvent event) {
      return event.verticalOffset >= 0;
    }

    @Override
    protected boolean continuous(final LinkedList<TextViewEvent> sequence, final TextViewEvent event) {
      return sequence.getFirst().isContinuousWith(event);
    }

    @Override
    protected void process(final LinkedList<TextViewEvent> sequence) {
      this.listener.handleViewEvents(sequence);
    }
  }

  void generateActiveEditorViewEvent() {
    this.execute(DisplayTask.of(new Runnable() {
      public void run() {
        viewportChanged(waitForActiveEditorReference());
      }
    }));
  }

  void handleViewEvents(final LinkedList<TextViewEvent> sequence) {
    this.execute(new Runnable() {
      public void run() {
        TextViewEvent event = sequence.getLast();

        process(event.time, VIEW, sequence, event.viewer);
      }
    });
  }

  private LineRegion region(final ITextViewer viewer) {
    return this.execute(new DisplayTask<LineRegion>() {
      public LineRegion call() {
        int top = viewer.getTopIndex();
        int bottom = viewer.getBottomIndex();

        return LineRegion.between(viewer.getDocument(), top, bottom).normalize();
      }
    });
  }

  private int verticalOffset(final ITextViewer viewer) {
    return this.execute(new DisplayTask<Integer>() {
      public Integer call() {
        return viewer.getTextWidget().getTopPixel();
      }
    });
  }

  public void editorOpened(final IEditorReference reference) {
    // ignore
  }

  public void editorClosed(final IEditorReference reference) {
    // ignore
  }

  public void editorActivated(final IEditorReference reference) {
    // ensures that a viewport change is always generated on editor activation,
    // this catches every user-to-editor view, as a side effect it breaks event continuation

    this.viewportChanged(reference);
  }

  public void editorDeactivated(final IEditorReference reference) {
    // ensures that pending events are flushed on editor deactivation, this primarily handles
    // proper user-to-editor view on shutdown, as a side effect it breaks event continuation

    this.events.flush();
  }

  public void editorVisible(final IEditorReference reference) {
    // ignore
  }

  public void editorHidden(final IEditorReference reference) {
    // ignore
  }

  public void editorBroughtToTop(final IEditorReference reference) {
    // ignore
  }

  public void editorInputChanged(final IEditorReference reference) {
    // ignore
  }

  public void viewportChanged(final IEditorReference reference) {
    final long time = this.currentTime();

    IEditorPart editor = dereferenceEditor(reference);
    ITextViewer viewer = Editors.getTextViewer(editor);

    LineRegion region = this.region(viewer);
    int verticalOffset = this.verticalOffset(viewer);

    this.events.push(new TextViewEvent(time, viewer, region, verticalOffset));
  }

  public void viewportChanged(final ITextViewer viewer, final int verticalOffset) {
    final long time = this.currentTime();

    LineRegion region = this.region(viewer);

    this.events.push(new TextViewEvent(time, viewer, region, verticalOffset));
  }
}
