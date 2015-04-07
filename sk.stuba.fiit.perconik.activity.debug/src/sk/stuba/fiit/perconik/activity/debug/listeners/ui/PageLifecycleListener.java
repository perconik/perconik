package sk.stuba.fiit.perconik.activity.debug.listeners.ui;

import org.eclipse.ui.IWorkbenchPage;

import sk.stuba.fiit.perconik.activity.debug.listeners.AbstractLifecycleListener;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;
import sk.stuba.fiit.perconik.core.listeners.PageListener;

@DebugImplementation
@Version("0.0.1.alpha")
public final class PageLifecycleListener extends AbstractLifecycleListener implements PageListener {
  public PageLifecycleListener() {}

  public void pageOpened(final IWorkbenchPage page) {
    this.mark(page, "page", "open");
  }

  public void pageClosed(final IWorkbenchPage page) {
    this.mark(page, "page", "close");
  }

  public void pageActivated(final IWorkbenchPage page) {
    this.mark(page, "page", "activate");
  }
}
