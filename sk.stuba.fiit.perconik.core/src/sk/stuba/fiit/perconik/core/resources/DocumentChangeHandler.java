package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.DocumentChangeListener;
import sk.stuba.fiit.perconik.core.resources.DocumentChangeHook.Support;

enum DocumentChangeHandler implements Handler<DocumentChangeListener>
{
	INSTANCE;
	
	private final Support support = new Support();
	
	public final void register(final DocumentChangeListener listener)
	{
		this.support.hook(DefaultResources.getPartResource(), listener);
	}

	public final void unregister(final DocumentChangeListener listener)
	{
		this.support.unhook(DefaultResources.getPartResource(), listener);
	}
}
