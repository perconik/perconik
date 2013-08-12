package sk.stuba.fiit.perconik.core.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class AbstractListenerService implements ListenerService
{
	protected AbstractListenerService()
	{
	}
	
	protected abstract ResourceService service();
	
	public final <L extends Listener> void register(final L listener)
	{
		for(Resource<? super L> resource: this.service().registerable((Class<L>) listener.getClass()))
		{
			resource.register(listener);
		}
	}

	public final <L extends Listener> void unregister(final L listener)
	{
		for(Resource<? super L> resource: this.service().registerable((Class<L>) listener.getClass()))
		{
			resource.unregister(listener);
		}
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
		for (Resource<?> resource: this.service().assignable(type))
		{
			resource.unregisterAll(type);
		}
	}

	public final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		List<L> listeners = Lists.newArrayList();
		
		for (Resource<? extends L> resource: this.service().assignable(type))
		{
			listeners.addAll(resource.registered(type));
		}
		
		return listeners;
	}

	public final Map<Resource<?>, Collection<Listener>> registrations()
	{
		Map<Resource<?>, Collection<Listener>> registrations = Maps.newHashMap();
		
		for (Resource<?> resource: this.service().registered())
		{
			registrations.put(resource, resource.registered(Listener.class));
		}
		
		return registrations;
	}
}
