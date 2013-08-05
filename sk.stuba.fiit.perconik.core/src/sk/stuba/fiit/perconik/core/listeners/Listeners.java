package sk.stuba.fiit.perconik.core.listeners;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import sk.stuba.fiit.perconik.core.resources.Resource;
import sk.stuba.fiit.perconik.core.resources.Resources;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}

	public static final <T extends Listener> void register(final T listener)
	{
		for(Resource<? super T> resource: Resources.registerable((Class<T>) listener.getClass()))
		{
			resource.register(listener);
		}
	}

	public static final <T extends Listener> void unregister(final T listener)
	{
		for(Resource<? super T> resource: Resources.registerable((Class<T>) listener.getClass()))
		{
			resource.unregister(listener);
		}
	}

	public static final Collection<Listener> registered()
	{
		return registered(Listener.class);
	}
	
	public static final <T extends Listener> Collection<T> registered(final Class<T> type)
	{
		List<T> listeners = Lists.newArrayList();
		
		for (Resource<? extends T> resource: Resources.assignable(type))
		{
			listeners.addAll(resource.getRegistered(type));
		}
		
		return listeners;
	}

	public static final Map<Resource<?>, Collection<Listener>> registeredMap()
	{
		Map<Resource<?>, Collection<Listener>> map = Maps.newHashMap();
		
		for (Resource<?> resource: Resources.registered())
		{
			map.put(resource, resource.getRegistered(Listener.class));
		}
		
		return map;
	}

	public static final void unregisterAll()
	{
		unregisterAll(Listener.class);
	}
	
	public static final void unregisterAll(final Class<? extends Listener> type)
	{
		for (Resource<?> resource: Resources.assignable(type))
		{
			resource.unregisterAll(type);
		}
	}
}
