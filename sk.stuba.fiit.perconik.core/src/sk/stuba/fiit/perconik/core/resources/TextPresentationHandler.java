package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.TextPresentationListener;
import sk.stuba.fiit.perconik.core.resources.TextPresentationHook.Support;

enum TextPresentationHandler implements Handler<TextPresentationListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final TextPresentationListener listener) {
    this.support.hook(DefaultResources.getEditorResource(), listener);
  }

  public void unregister(final TextPresentationListener listener) {
    this.support.unhook(DefaultResources.getEditorResource(), listener);
  }
}
