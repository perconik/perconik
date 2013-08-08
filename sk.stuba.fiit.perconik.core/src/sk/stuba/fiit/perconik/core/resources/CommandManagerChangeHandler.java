package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.CommandManagerChangeListener;


enum CommandManagerChangeHandler implements Handler<CommandManagerChangeListener>
{
	INSTANCE;
	
	public final void register(final CommandManagerChangeListener listener)
	{
		// TODO
	}

	public final void unregister(final CommandManagerChangeListener listener)
	{
		// TODO
	}
}
