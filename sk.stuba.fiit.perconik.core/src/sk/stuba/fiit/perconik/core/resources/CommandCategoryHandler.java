package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandCategoryListener;

@Unimplemented
enum CommandCategoryHandler implements Handler<CommandCategoryListener> {
  INSTANCE;

  public void register(final CommandCategoryListener listener) {
    throw new UnsupportedResourceException("Not implemented yet");
  }

  public void unregister(final CommandCategoryListener listener) {
    throw new UnsupportedResourceException("Not implemented yet");
  }
}
