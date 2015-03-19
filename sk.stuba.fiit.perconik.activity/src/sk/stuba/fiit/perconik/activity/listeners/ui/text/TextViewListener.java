package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.ViewportListener;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.PRE_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextViewListener.Action.VIEW;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
@Unsupported
public final class TextViewListener extends AbstractTextListener implements ViewportListener {
  static final TimeValue viewEventWindow = of(500, MILLISECONDS);

  private final TextViewEventProcessor processor;

  public TextViewListener() {
    this.processor = new TextViewEventProcessor(this);

    PRE_UNREGISTER.add(this, new NamedRunnable(this.getClass(), "UnsentViewHandler") {
      public void run() {
        handleUnsentViewOnUnregistration();
      }
    });
  }

  enum Action implements CommonEventListener.Action {
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

  void process(final long time, final Action action, final ITextViewer viewer) {
    IEditorPart editor = Editors.forTextViewer(viewer);
    IDocument document = Editors.getDocument(viewer);
    UnderlyingView<?> view = UnderlyingView.resolve(document, editor);

    int top = viewer.getTopIndex();
    int bottom = viewer.getBottomIndex();

    LineRegion region = LineRegion.between(document, top, bottom);

    this.send(action.getPath(), build(time, action, editor, view, region));
  }

  static final class TextViewEventProcessor extends ContinuousEventWindow<TextViewListener, TextViewEvent> {
    protected TextViewEventProcessor(final TextViewListener listener) {
      super(listener, "view", viewEventWindow);
    }

    @Override
    protected boolean accept(final LinkedList<TextViewEvent> sequence, final TextViewEvent event) {
      return event.verticalOffset != 0;
    }

    @Override
    protected boolean continuous(final LinkedList<TextViewEvent> sequence, final TextViewEvent event) {
      return sequence.getLast().isContinuousWith(event);
    }

    @Override
    protected void process(final LinkedList<TextViewEvent> sequence) {
      this.listener.handleAcceptedView(sequence.getLast());
    }
  }

  void handleAcceptedView(final TextViewEvent event) {
    execute(new Runnable() {
      public void run() {
        process(event.time, VIEW, event.viewer);
      }
    });
  }

  void handleUnsentViewOnUnregistration() {
    this.processor.flush();
  }

  public void viewportChanged(final ITextViewer viewer, final int verticalOffset) {
    final long time = this.currentTime();

    this.processor.push(new TextViewEvent(time, viewer, verticalOffset));
  }
}
