package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.ui.IViewReference;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.ViewListener;

public final class ViewDebugListener extends AbstractDebugListener implements ViewListener {
  public ViewDebugListener() {}

  public ViewDebugListener(final DebugConsole console) {
    super(console);
  }

  public void viewOpened(final IViewReference reference) {
    this.printHeader("View opened");
    this.printViewReference(reference);
  }

  public void viewClosed(final IViewReference reference) {
    this.printHeader("View closed");
    this.printViewReference(reference);
  }

  public void viewActivated(final IViewReference reference) {
    this.printHeader("View activated");
    this.printViewReference(reference);
  }

  public void viewDeactivated(final IViewReference reference) {
    this.printHeader("View deactivated");
    this.printViewReference(reference);
  }

  public void viewVisible(final IViewReference reference) {
    this.printHeader("View visible");
    this.printViewReference(reference);
  }

  public void viewHidden(final IViewReference reference) {
    this.printHeader("View hidden");
    this.printViewReference(reference);
  }

  public void viewBroughtToTop(final IViewReference reference) {
    this.printHeader("View brought to top");
    this.printViewReference(reference);
  }

  public void viewInputChanged(final IViewReference reference) {
    this.printHeader("View input changed");
    this.printViewReference(reference);
  }

  private void printViewReference(final IViewReference reference) {
    this.put(Debug.dumpViewReference(reference));
  }
}
