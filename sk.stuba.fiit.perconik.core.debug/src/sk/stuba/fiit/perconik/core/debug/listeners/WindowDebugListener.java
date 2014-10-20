package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

public final class WindowDebugListener extends AbstractDebugListener implements WindowListener {
  public WindowDebugListener() {}

  public WindowDebugListener(final DebugConsole console) {
    super(console);
  }

  public void windowOpened(final IWorkbenchWindow window) {
    this.printHeader("Window opened");
    this.printWindow(window);
  }

  public void windowClosed(final IWorkbenchWindow window) {
    this.printHeader("Window closed");
    this.printWindow(window);
  }

  public void windowActivated(final IWorkbenchWindow window) {
    this.printHeader("Window activated");
    this.printWindow(window);
  }

  public void windowDeactivated(final IWorkbenchWindow window) {
    this.printHeader("Window deactivated");
    this.printWindow(window);
  }

  private void printWindow(final IWorkbenchWindow window) {
    this.put(Debug.dumpWindow(window));
  }
}
