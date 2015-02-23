package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.TextInputListener;
import sk.stuba.fiit.perconik.core.resources.TextInputHook.Support;

enum TextInputHandler implements Handler<TextInputListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final TextInputListener listener) {
    this.support.hook(DefaultResources.getEditorResource(), listener);
  }

  public void unregister(final TextInputListener listener) {
    this.support.unhook(DefaultResources.getEditorResource(), listener);
  }
}
