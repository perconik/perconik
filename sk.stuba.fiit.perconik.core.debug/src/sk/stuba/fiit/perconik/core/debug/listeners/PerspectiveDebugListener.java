package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;

public final class PerspectiveDebugListener extends AbstractDebugListener implements PerspectiveListener {
  public PerspectiveDebugListener() {}

  public PerspectiveDebugListener(final DebugConsole console) {
    super(console);
  }

  public void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.printHeader("Perspective opened");
    this.printPage(page);
    this.printPerspectiveDescriptor(descriptor);
  }

  public void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.printHeader("Perspective closed");
    this.printPage(page);
    this.printPerspectiveDescriptor(descriptor);
  }

  public void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.printHeader("Perspective activated");
    this.printPage(page);
    this.printPerspectiveDescriptor(descriptor);
  }

  public void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.printHeader("Perspective deactivated");
    this.printPage(page);
    this.printPerspectiveDescriptor(descriptor);
  }

  public void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.printHeader("Perspective pre deactivate");
    this.printPage(page);
    this.printPerspectiveDescriptor(descriptor);
  }

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final String change) {}

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final IWorkbenchPartReference reference, final String change) {
    this.printHeader("Perspective changed");
    this.printPage(page);
    this.printPerspectiveDescriptor(descriptor);
    this.printPartReference(reference);
    this.printLine("change", change);
  }

  public void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    this.printHeader("Perspective saved");
    this.printPage(page);
    this.put(Debug.dumpBlock("before:", Debug.dumpPerspectiveDescriptor(before)));
    this.put(Debug.dumpBlock("after:", Debug.dumpPerspectiveDescriptor(after)));
  }

  private void printPage(final IWorkbenchPage page) {
    this.put(Debug.dumpPage(page));
  }

  private void printPartReference(final IWorkbenchPartReference reference) {
    this.put(Debug.dumpPartReference(reference));
  }

  private void printPerspectiveDescriptor(final IPerspectiveDescriptor descriptor) {
    this.put(Debug.dumpPerspectiveDescriptor(descriptor));
  }
}
