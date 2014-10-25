package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
@Version("0.0.1")
public final class PerspectiveListener extends SharingEventListener implements sk.stuba.fiit.perconik.core.listeners.PerspectiveListener {
  public PerspectiveListener() {}

  public final void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public final void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public final void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public final void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public final void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public final void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final String change) {}

  public final void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final IWorkbenchPartReference reference, final String change) {}

  public final void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {}
}
