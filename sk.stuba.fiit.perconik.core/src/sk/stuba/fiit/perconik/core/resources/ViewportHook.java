package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.IViewportListener;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.ViewportListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferenceEditor;

final class ViewportHook extends InternalHook<ITextViewer, ViewportListener> implements EditorListener {
  ViewportHook(final ViewportListener listener) {
    super(new TextViewerHandler(listener));
  }

  static final class Support extends AbstractHookSupport<ViewportHook, ITextViewer, ViewportListener> {
    public Hook<ITextViewer, ViewportListener> create(final ViewportListener listener) {
      return new ViewportHook(listener);
    }
  }

  private static final class ViewportListenerProxy extends AbstractListenerProxy<ViewportListener> implements IViewportListener {
    final ITextViewer viewer;

    ViewportListenerProxy(final ViewportListener listener, final ITextViewer viewer) {
      super(listener);

      this.viewer = requireNonNull(viewer);
    }

    public void viewportChanged(final int verticalOffset) {
      this.listener.viewportChanged(this.viewer, verticalOffset);
    }
  }

  private static final class TextViewerHandler extends InternalHandler<ITextViewer, ViewportListener> {
    TextViewerHandler(final ViewportListener listener) {
      super(ITextViewer.class, listener);
    }

    public void register(final ITextViewer viewer) {
      viewer.addViewportListener(new ViewportListenerProxy(this.listener, viewer));
    }

    public void unregister(final ITextViewer viewer) {
      viewer.removeViewportListener(new ViewportListenerProxy(this.listener, viewer));
    }
  }

  @Override
  void preRegisterInternal() {
    Hooks.addTextViewersAsynchronouslyTo(this);
  }

  public void editorOpened(final IEditorReference reference) {
    Hooks.addNonNull(this, Editors.getTextViewer(dereferenceEditor(reference)));
  }

  public final void editorClosed(final IEditorReference reference) {
    Hooks.removeNonNull(this, Editors.getTextViewer(dereferenceEditor(reference)));
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}
}
