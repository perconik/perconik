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
	
	public final <L extends Listener> void register(final Class<L> type, final Resource<? super L> resource)
	{
		Preconditions.checkNotNull(type);

		resource.preRegister();
		
		this.map().put(type, resource);
		
		resource.postRegister();
	}
	
	public final <L extends Listener> void unregister(final Class<L> type, final Resource<? super L> resource)
	{
		resource.preUnregister();
		resource.unregisterAll(type);
		
		this.map().remove(type, resource);
		
		resource.postUnregister();
	}
	
	public final <L extends Listener> void unregisterAll(final Class<L> type)
	{
		for (Resource<? super L> resource: this.registrable(type))
		{
			this.unregister(type, resource);
		}
	}
}
