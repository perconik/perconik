package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.listeners.CommandCategoryListener;

@Unimplemented
enum CommandCategoryHandler implements Handler<CommandCategoryListener>
{
	INSTANCE;
	
	public final void register(final CommandCategoryListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}

	public final void unregister(final CommandCategoryListener listener)
	{
		throw new UnsupportedResourceException("Not implemented yet");
	}
}
