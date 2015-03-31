package sk.stuba.fiit.perconik.activity.debug.listeners.ui;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.activity.debug.listeners.AbstractLifecycleListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;

import static java.util.Arrays.asList;

@DebugImplementation
@Version("0.0.1.alpha")
public final class PerspectiveLifecycleListener extends AbstractLifecycleListener implements PerspectiveListener {
  public PerspectiveLifecycleListener() {}

  public void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.mark(asList(page, descriptor), "perspective", "open");
  }

  public void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.mark(asList(page, descriptor), "perspective", "close");
  }

  public void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.mark(asList(page, descriptor), "perspective", "activate");
  }

  public void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.mark(asList(page, descriptor), "perspective", "deactivate");
  }

  public void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.mark(asList(page, descriptor), "perspective", "pre deactivate");
  }

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final String change) {
    this.mark(asList(page, descriptor), "perspective", "change");
  }

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final IWorkbenchPartReference reference, final String change) {
    this.mark(asList(page, descriptor), "perspective", "change (with reference)");
  }

  public void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    this.mark(asList(page, before), "perspective", "save");
  }
}
