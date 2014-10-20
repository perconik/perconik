package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.resources.PartHook.Support;

enum PartHandler implements Handler<PartListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final PartListener listener) {
    this.support.hook(DefaultResources.getWindowResource(), listener);
  }

  public void unregister(final PartListener listener) {
    this.support.unhook(DefaultResources.getWindowResource(), listener);
  }
}
