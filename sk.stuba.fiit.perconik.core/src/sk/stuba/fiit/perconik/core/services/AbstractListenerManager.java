package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

public abstract class AbstractListenerManager extends AbstractManager implements ListenerManager
{
	protected AbstractListenerManager()
	{
	}
	
	protected abstract ResourceManager manager();
	
	public final <L extends Listener> void register(final L listener)
	{
		for(Resource<? super L> resource: this.manager().registerable((Class<L>) listener.getClass()))
		{
			resource.register(listener);
		}
	}

	public final <L extends Listener> void unregister(final L listener)
	{
		for(Resource<? super L> resource: this.manager().registerable((Class<L>) listener.getClass()))
		{
			resource.unregister(listener);
		}
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
		for (Resource<?> resource: this.manager().assignable(type))
		{
			resource.unregisterAll(type);
		}
	}
}
