package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.core.resources.PerspectiveHook.Support;

enum PerspectiveHandler implements Handler<PerspectiveListener>
{
	INSTANCE;
	
	private final Support support = new Support();
	
	public final void register(final PerspectiveListener listener)
	{
		this.support.hook(DefaultResources.window, listener);
	}

	public final void unregister(final PerspectiveListener listener)
	{
		this.support.unhook(DefaultResources.window, listener);
	}
}
