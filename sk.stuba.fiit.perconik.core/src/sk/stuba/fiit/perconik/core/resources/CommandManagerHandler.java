package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandManagerListener;

@Unimplemented
enum CommandManagerHandler implements Handler<CommandManagerListener> {
  INSTANCE;

  public void register(final CommandManagerListener listener) {
    throw new UnsupportedResourceException("Not implemented yet");

    // TODO
    //		final Runnable addListener = new Runnable() {
    //			public final void run() {
    //			}
    //		};
    //
    //		Display.getDefault().asyncExec(addListener);
  }

  public void unregister(final CommandManagerListener listener) {
    throw new UnsupportedResourceException("Not implemented yet");
  }
}
