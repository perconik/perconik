package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.PartListener;

public final class PartDebugListener extends AbstractDebugListener implements PartListener {
  public PartDebugListener() {}

  public PartDebugListener(final DebugConsole console) {
    super(console);
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    this.printHeader("Part opened");
    this.printPartReference(reference);
  }

  public void partClosed(final IWorkbenchPartReference reference) {
    this.printHeader("Part closed");
    this.printPartReference(reference);
  }

  public void partActivated(final IWorkbenchPartReference reference) {
    this.printHeader("Part activated");
    this.printPartReference(reference);
  }

  public void partDeactivated(final IWorkbenchPartReference reference) {
    this.printHeader("Part deactivated");
    this.printPartReference(reference);
  }

  public void partVisible(final IWorkbenchPartReference reference) {
    this.printHeader("Part visible");
    this.printPartReference(reference);
  }

  public void partHidden(final IWorkbenchPartReference reference) {
    this.printHeader("Part hidden");
    this.printPartReference(reference);
  }

  public void partBroughtToTop(final IWorkbenchPartReference reference) {
    this.printHeader("Part brought to top");
    this.printPartReference(reference);
  }

  public void partInputChanged(final IWorkbenchPartReference reference) {
    this.printHeader("Part input changed");
    this.printPartReference(reference);
  }

  private void printPartReference(final IWorkbenchPartReference reference) {
    this.put(Debug.dumpPartReference(reference));
  }
}
