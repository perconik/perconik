package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import java.util.LinkedList;
import java.util.Map;

import javax.annotation.concurrent.GuardedBy;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IViewportListener;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingView;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.PRE_UNREGISTER;
import static sk.stuba.fiit.perconik.activity.listeners.ui.text.TextViewListener.Action.VIEW;
import static sk.stuba.fiit.perconik.activity.serializers.ui.Ui.dereferenceEditor;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class TextViewListener extends AbstractTextOperationListener implements EditorListener {
  static final long viewEventWindow = 500;

  private final Object lock = new Object();

  @GuardedBy("lock")
  private final Map<ISourceViewer, IViewportListener> sourceViewerListeners;

  private final TextViewEventProcessor processor;

  public TextViewListener() {
    this.sourceViewerListeners = newHashMap();

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

  // TODO remove and refactor once core supports ViewportListener
  private final class ViewportListener implements IViewportListener {
    private final IEditorPart editor;

    private final ISourceViewer viewer;

    ViewportListener(final IEditorPart editor, final ISourceViewer viewer) {
      this.editor = requireNonNull(editor);
      this.viewer = requireNonNull(viewer);
    }

    @SuppressWarnings("synthetic-access")
    public void viewportChanged(final int verticalOffset) {
      final long time = currentTime();

      TextViewListener.this.processor.push(new TextViewEvent(time, this.editor, this.viewer, verticalOffset));
    }
  }

  void hookViewportListener(final IEditorReference reference) {
    IEditorPart editor = dereferenceEditor(reference);
    ISourceViewer viewer = Editors.getSourceViewer(editor);

    synchronized (this.lock) {
      if (!this.sourceViewerListeners.containsKey(viewer)) {
        ViewportListener listener = new ViewportListener(editor, viewer);

        this.sourceViewerListeners.put(viewer, listener);

        viewer.addViewportListener(listener);
      }
    }
  }

  void unhookViewportListener(final IEditorReference reference) {
    IEditorPart editor = dereferenceEditor(reference);
    ISourceViewer viewer = Editors.getSourceViewer(editor);

    synchronized (this.lock) {
      IViewportListener listener = this.sourceViewerListeners.remove(viewer);

      if (listener != null) {
        viewer.removeViewportListener(listener);
      }
    }
  }

  void process(final long time, final Action action, final IEditorPart editor, final ISourceViewer viewer) {
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
    this.execute(new Runnable() {
      public void run() {
        process(event.time, VIEW, event.editor, event.viewer);
      }
    });
  }

  void handleUnsentViewOnUnregistration() {
    this.processor.flush();
  }

  public void editorOpened(final IEditorReference reference) {
    this.execute(new Runnable() {
      public void run() {
        hookViewportListener(reference);
      }
    });
  }

  public void editorClosed(final IEditorReference reference) {
    this.execute(new Runnable() {
      public void run() {
        unhookViewportListener(reference);
      }
    });
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}
}
