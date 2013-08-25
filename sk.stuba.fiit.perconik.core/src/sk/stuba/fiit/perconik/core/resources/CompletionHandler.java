package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.CompletionListener;
import sk.stuba.fiit.perconik.core.resources.CompletionHook.Support;

enum CompletionHandler implements Handler<CompletionListener>
{
	INSTANCE;
	
	private final Support support = new Support();
	
	public final void register(final CompletionListener listener)
	{
		this.support.hook(DefaultResources.getEditorResource(), listener);
	}

	public final void unregister(final CompletionListener listener)
	{
		this.support.unhook(DefaultResources.getEditorResource(), listener);
	}
}
