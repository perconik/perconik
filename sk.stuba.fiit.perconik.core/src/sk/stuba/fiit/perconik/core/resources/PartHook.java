package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

final class PartHook extends InternalHook<IWorkbenchWindow, PartListener> implements WindowListener {
  PartHook(final PartListener listener) {
    super(new WindowHandler(listener));
  }

  static final class Support extends AbstractHookSupport<PartHook, IWorkbenchWindow, PartListener> {
    public Hook<IWorkbenchWindow, PartListener> create(final PartListener listener) {
      return new PartHook(listener);
    }
  }

  private static final class WindowHandler extends InternalHandler<IWorkbenchWindow, PartListener> {
    WindowHandler(final PartListener listener) {
      super(IWorkbenchWindow.class, listener);
    }

    public void register(final IWorkbenchWindow window) {
      window.getPartService().addPartListener(this.listener);
    }

    public void unregister(final IWorkbenchWindow window) {
      window.getPartService().removePartListener(this.listener);
    }
  }

  @Override
  void preRegisterInternal() {
    Hooks.addWindowsAsynchronouslyTo(this);
  }

  public void windowOpened(final IWorkbenchWindow window) {
    this.add(window);
  }

  public void windowClosed(final IWorkbenchWindow window) {
    this.remove(window);
  }

  public void windowActivated(final IWorkbenchWindow window) {}

  public void windowDeactivated(final IWorkbenchWindow window) {}
}
