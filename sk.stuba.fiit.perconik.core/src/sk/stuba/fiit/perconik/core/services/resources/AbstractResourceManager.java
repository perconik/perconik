package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.IllegalListenerClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.AbstractManager;
import com.google.common.collect.SetMultimap;

public abstract class AbstractResourceManager extends AbstractManager implements ResourceManager
{
	protected AbstractResourceManager()
	{
	}
	
	protected abstract SetMultimap<Class<? extends Listener>, Resource<?>> multimap();

	protected static final <L extends Listener> void check(final Class<L> type)
	{
		if (!Listener.class.isAssignableFrom(type))
		{
			throw new IllegalListenerClassException(type.getName() + " is not assignable to " + Listener.class.getName());
		}
	}
	
	public final <L extends Listener> void register(final Class<L> type, final Resource<? super L> resource)
	{
		check(type);

		resource.preRegister();
		
		this.multimap().put(type, resource);
		
		resource.postRegister();
	}
	
	public final <L extends Listener> void unregister(final Class<L> type, final Resource<? super L> resource)
	{
		check(type);

		resource.preUnregister();
		resource.unregisterAll(type);
		
		this.multimap().remove(type, resource);
		
		resource.postUnregister();
	}
}
