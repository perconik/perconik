package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandContextManagerListener;

@Unimplemented
enum CommandContextManagerHandler implements Handler<CommandContextManagerListener>
{
	INSTANCE;
	
	public final void register(final CommandContextManagerListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}

	public final void unregister(final CommandContextManagerListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}
}
