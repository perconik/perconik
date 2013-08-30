package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandContextListener;

enum CommandContextHandler implements Handler<CommandContextListener>
{
	INSTANCE;
	
	public final void register(final CommandContextListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}

	public final void unregister(final CommandContextListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}
}
