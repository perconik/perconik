package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.resources.SelectionHook.Support;

enum SelectionHandler implements Handler<SelectionListener>
{
	INSTANCE;

	private final Support support = new Support();
	
	public final void register(final SelectionListener listener)
	{
		this.support.hook(DefaultResources.window, listener);
	}

	public final void unregister(final SelectionListener listener)
	{
		this.support.unhook(DefaultResources.window, listener);
	}
}
