package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.core.resources.PerspectiveHook.Support;

enum PerspectiveHandler implements Handler<PerspectiveListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final PerspectiveListener listener) {
    this.support.hook(DefaultResources.getWindowResource(), listener);
  }

  public void unregister(final PerspectiveListener listener) {
    this.support.unhook(DefaultResources.getWindowResource(), listener);
  }
}
