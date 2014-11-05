package sk.stuba.fiit.perconik.activity.listeners;

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
public final class PartListener extends SharingEventListener implements sk.stuba.fiit.perconik.core.listeners.PartListener {
  public PartListener() {}

  enum Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE,

    SHOW,

    HIDE,

    BRING_TO_TOP,

    CHANGE_INPUT;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "part", this);
      this.path = actionPath(this.name);
    }
  }

  public void partOpened(final IWorkbenchPartReference reference) {}

  public void partClosed(final IWorkbenchPartReference reference) {}

  public void partActivated(final IWorkbenchPartReference reference) {}

  public void partDeactivated(final IWorkbenchPartReference reference) {}

  public void partVisible(final IWorkbenchPartReference reference) {}

  public void partHidden(final IWorkbenchPartReference reference) {}

  public void partBroughtToTop(final IWorkbenchPartReference reference) {}

  public void partInputChanged(final IWorkbenchPartReference reference) {}
}
