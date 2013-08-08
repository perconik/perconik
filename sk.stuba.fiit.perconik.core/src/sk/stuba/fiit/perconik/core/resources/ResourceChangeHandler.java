package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.core.resources.ResourcesPlugin;
import sk.stuba.fiit.perconik.core.listeners.ResourceChangeListener;

enum ResourceChangeHandler implements Handler<ResourceChangeListener>
{
	INSTANCE;
	
	public final void register(final ResourceChangeListener listener)
	{
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, Handlers.mask(listener));
	}

	public final void unregister(final ResourceChangeListener listener)
	{
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
	}
}
