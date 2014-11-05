package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbenchPage;

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
public final class PageListener extends SharingEventListener implements sk.stuba.fiit.perconik.core.listeners.PageListener {
  public PageListener() {}

  enum Action {
    OPEN,

    CLOSE,

    ACTIVATE;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "page", this);
      this.path = actionPath(this.name);
    }
  }

  public void pageOpened(final IWorkbenchPage page) {}

  public void pageClosed(final IWorkbenchPage page) {}

  public void pageActivated(final IWorkbenchPage page) {}
}
