package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

final class PartHook extends InternalHook<IWorkbenchWindow, PartListener> implements WindowListener {
  PartHook(final PartListener listener) {
    super(new PageHandler(listener));
  }

  static final class Support extends AbstractHookSupport<PartHook, IWorkbenchWindow, PartListener> {
    public final Hook<IWorkbenchWindow, PartListener> create(final PartListener listener) {
      return new PartHook(listener);
    }
  }

  private static final class PageHandler extends InternalHandler<IWorkbenchWindow, PartListener> {
    PageHandler(final PartListener listener) {
      super(IWorkbenchWindow.class, listener);
    }

    public final void register(final IWorkbenchWindow window) {
      window.getPartService().addPartListener(this.listener);
    }

    public final void unregister(final IWorkbenchWindow window) {
      window.getPartService().removePartListener(this.listener);
    }
  }

  @Override
  final void preRegisterInternal() {
    Hooks.addWindowsAsynchronouslyTo(this);
  }

  public final void windowOpened(final IWorkbenchWindow window) {
    this.add(window);
  }

  public final void windowClosed(final IWorkbenchWindow window) {
    this.remove(window);
  }

  public final void windowActivated(final IWorkbenchWindow window) {}

  public final void windowDeactivated(final IWorkbenchWindow window) {}
}
