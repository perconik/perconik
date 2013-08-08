package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.CommandChangeListener;


enum CommandChangeHandler implements Handler<CommandChangeListener>
{
	INSTANCE;
	
	public final void register(final CommandChangeListener listener)
	{
		// TODO
	}

	public final void unregister(final CommandChangeListener listener)
	{
		// TODO
	}
}
