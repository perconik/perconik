package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.resources.PageHook.Support;

enum PageHandler implements Handler<PageListener>
{
	INSTANCE;
	
	private final Support support = new Support();
	
	public final void register(final PageListener listener)
	{
		this.support.hook(DefaultResources.window, listener);
	}

	public final void unregister(final PageListener listener)
	{
		this.support.unhook(DefaultResources.window, listener);
	}
}
