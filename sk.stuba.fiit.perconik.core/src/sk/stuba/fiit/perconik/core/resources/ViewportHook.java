package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.IViewportListener;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.ViewportListener;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferencePart;

final class ViewportHook extends InternalHook<ITextViewer, ViewportListener> implements PartListener {
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

      this.viewer = checkNotNull(viewer);
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

  public void partOpened(final IWorkbenchPartReference reference) {
    Hooks.addNonNull(this, Parts.getTextViewer(dereferencePart(reference)));
  }

  public final void partClosed(final IWorkbenchPartReference reference) {
    Hooks.removeNonNull(this, Parts.getTextViewer(dereferencePart(reference)));
  }

  public void partActivated(final IWorkbenchPartReference reference) {}

  public void partDeactivated(final IWorkbenchPartReference reference) {}

  public void partVisible(final IWorkbenchPartReference reference) {}

  public void partHidden(final IWorkbenchPartReference reference) {}

  public void partBroughtToTop(final IWorkbenchPartReference reference) {}

  public void partInputChanged(final IWorkbenchPartReference reference) {}
}
