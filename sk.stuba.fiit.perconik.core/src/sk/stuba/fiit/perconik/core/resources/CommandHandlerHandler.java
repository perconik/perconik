package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandHandlerListener;

@Unimplemented
enum CommandHandlerHandler implements Handler<CommandHandlerListener> {
  INSTANCE;

  public void register(final CommandHandlerListener listener) {
    throw new UnsupportedResourceException("Not implemented yet");
  }

  public void unregister(final CommandHandlerListener listener) {
    throw new UnsupportedResourceException("Not implemented yet");
  }
}
