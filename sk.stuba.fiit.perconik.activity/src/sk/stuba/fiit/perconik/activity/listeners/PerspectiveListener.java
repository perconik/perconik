package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.annotations.Version;

import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionName;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionPath;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class PerspectiveListener extends SharingEventListener implements sk.stuba.fiit.perconik.core.listeners.PerspectiveListener {
  public PerspectiveListener() {}

  enum Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE,

    CHANGE,

    SAVE;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "perspective", this);
      this.path = actionPath(this.name);
    }
  }

  public void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {}

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final String change) {}

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final IWorkbenchPartReference reference, final String change) {}

  public void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {}
}
