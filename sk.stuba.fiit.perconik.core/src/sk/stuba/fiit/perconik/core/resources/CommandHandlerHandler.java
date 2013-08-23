package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.CommandHandlerListener;

enum CommandHandlerHandler implements Handler<CommandHandlerListener>
{
	INSTANCE;
	
	public final void register(final CommandHandlerListener listener)
	{
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public final void unregister(final CommandHandlerListener listener)
	{
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
