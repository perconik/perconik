package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.core.resources.ResourcesPlugin;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;

enum ResourceHandler implements Handler<ResourceListener>
{
	INSTANCE;
	
	public final void register(final ResourceListener listener)
	{
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, Handlers.mask(listener));
	}

	public final void unregister(final ResourceListener listener)
	{
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
	}
}
