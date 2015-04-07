package sk.stuba.fiit.perconik.activity.debug.listeners.ui;

import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.debug.listeners.AbstractLifecycleListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

@DebugImplementation
@Version("0.0.1.alpha")
public final class WindowLifecycleListener extends AbstractLifecycleListener implements WindowListener {
  public WindowLifecycleListener() {}

  public void windowOpened(final IWorkbenchWindow window) {
    this.mark(window, "window", "open");
  }

  public void windowClosed(final IWorkbenchWindow window) {
    this.mark(window, "window", "close");
  }

  public void windowActivated(final IWorkbenchWindow window) {
    this.mark(window, "window", "activate");
  }

  public void windowDeactivated(final IWorkbenchWindow window) {
    this.mark(window, "window", "deactivate");
  }
}
