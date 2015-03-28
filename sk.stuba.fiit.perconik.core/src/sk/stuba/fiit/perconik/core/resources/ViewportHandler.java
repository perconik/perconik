package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.ViewportListener;
import sk.stuba.fiit.perconik.core.resources.ViewportHook.Support;

enum ViewportHandler implements Handler<ViewportListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final ViewportListener listener) {
    this.support.hook(DefaultResources.getPartResource(), listener);
  }

  public void unregister(final ViewportListener listener) {
    this.support.unhook(DefaultResources.getPartResource(), listener);
  }
}
