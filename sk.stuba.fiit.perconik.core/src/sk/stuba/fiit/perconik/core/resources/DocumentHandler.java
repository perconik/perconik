package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.resources.DocumentHook.Support;

enum DocumentHandler implements Handler<DocumentListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final DocumentListener listener) {
    this.support.hook(DefaultResources.getEditorResource(), listener);
  }

  public void unregister(final DocumentListener listener) {
    this.support.unhook(DefaultResources.getEditorResource(), listener);
  }
}
