package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.ViewListener;

enum ViewHandler implements Handler<ViewListener> {
  INSTANCE;

  private static final class PartFilter extends InternalFilter<ViewListener> implements PartListener {
    PartFilter(final ViewListener listener) {
      super(listener);
    }

    public void partOpened(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewOpened((IViewReference) reference);
      }
    }

    public void partClosed(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewClosed((IViewReference) reference);
      }
    }

    public void partActivated(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewActivated((IViewReference) reference);
      }
    }

    public void partDeactivated(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewDeactivated((IViewReference) reference);
      }
    }

    public void partVisible(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewVisible((IViewReference) reference);
      }
    }

    public void partHidden(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewHidden((IViewReference) reference);
      }
    }

    public void partBroughtToTop(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewBroughtToTop((IViewReference) reference);
      }
    }

    public void partInputChanged(final IWorkbenchPartReference reference) {
      if (reference instanceof IViewReference) {
        this.listener.viewInputChanged((IViewReference) reference);
      }
    }
  }

  public void register(final ViewListener listener) {
    PartHandler.INSTANCE.register(new PartFilter(listener));
  }

  public void unregister(final ViewListener listener) {
    PartHandler.INSTANCE.unregister(new PartFilter(listener));
  }
}
