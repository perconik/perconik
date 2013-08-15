package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.base.Preconditions;
import com.google.common.collect.SetMultimap;

public abstract class AbstractResourceManager extends AbstractManager implements ResourceManager
{
	protected AbstractResourceManager()
	{
	}
	
	protected abstract SetMultimap<Class<? extends Listener>, Resource<?>> map();
	
	public final <L extends Listener> void register(final Class<L> type, final Resource<L> resource)
	{
		Preconditions.checkNotNull(type);
		Preconditions.checkNotNull(resource);
		
		this.map().put(type, resource);
	}
	
	public final <L extends Listener> void unregister(final Class<L> type, final Resource<L> resource)
	{
		resource.unregisterAll(type);
		
		this.map().remove(type, resource);
	}
}
