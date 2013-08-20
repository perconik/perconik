package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ForwardingManager;
import com.google.common.collect.SetMultimap;

public abstract class ForwardingResourceManager extends ForwardingManager implements ResourceManager
{
	protected ForwardingResourceManager()
	{
	}

	@Override
	protected abstract ResourceManager delegate(); 

	public <L extends Listener> void register(Class<L> type, Resource<? super L> resource)
	{
		this.delegate().register(type, resource);
	}

	public <L extends Listener> void unregister(Class<L> type, Resource<? super L> resource)
	{
		this.delegate().unregister(type, resource);
	}

	public <L extends Listener> void unregisterAll(Class<L> type)
	{
		this.delegate().unregisterAll(type);
	}

	public <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type)
	{
		return this.delegate().assignable(type);
	}

	public <L extends Listener> Set<Resource<? super L>> registrable(Class<L> type)
	{
		return this.delegate().registrable(type);
	}

	public SetMultimap<Class<? extends Listener>, Resource<?>> registrations()
	{
		return this.delegate().registrations();
	}
}
