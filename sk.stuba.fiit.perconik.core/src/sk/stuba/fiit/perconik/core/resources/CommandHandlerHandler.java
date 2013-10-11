package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandHandlerListener;

@Unimplemented
enum CommandHandlerHandler implements Handler<CommandHandlerListener>
{
	INSTANCE;
	
	public final void register(final CommandHandlerListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}

	public final void unregister(final CommandHandlerListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}
}
