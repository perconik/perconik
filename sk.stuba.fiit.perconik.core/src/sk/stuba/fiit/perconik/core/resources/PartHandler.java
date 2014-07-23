package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.resources.PartHook.Support;

enum PartHandler implements Handler<PartListener>
{
	INSTANCE;
	
	private final Support support = new Support();
	
	public final void register(final PartListener listener)
	{
		this.support.hook(DefaultResources.getWindowResource(), listener);
	}

	public final void unregister(final PartListener listener)
	{
		this.support.unhook(DefaultResources.getWindowResource(), listener);
	}
}
