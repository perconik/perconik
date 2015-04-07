package sk.stuba.fiit.perconik.activity.debug.listeners.ui;

import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.activity.debug.listeners.AbstractLifecycleListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;
import sk.stuba.fiit.perconik.core.listeners.PartListener;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.ui.Ui.dereferencePart;

@DebugImplementation
@Version("0.0.1.alpha")
public final class PartLifecycleListener extends AbstractLifecycleListener implements PartListener {
  public PartLifecycleListener() {}

  public void partOpened(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "open");
  }

  public void partClosed(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "close");
  }

  public void partActivated(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "activate");
  }

  public void partDeactivated(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "deactivate");
  }

  public void partVisible(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "show");
  }

  public void partHidden(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "hide");
  }

  public void partBroughtToTop(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "bring to top");
  }

  public void partInputChanged(final IWorkbenchPartReference reference) {
    this.mark(asList(reference, dereferencePart(reference)), "part", "change input");
  }
}
