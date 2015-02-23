package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.TextListener;
import sk.stuba.fiit.perconik.core.resources.TextHook.Support;

enum TextHandler implements Handler<TextListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final TextListener listener) {
    this.support.hook(DefaultResources.getEditorResource(), listener);
  }

  public void unregister(final TextListener listener) {
    this.support.unhook(DefaultResources.getEditorResource(), listener);
  }
}
