package sk.stuba.fiit.perconik.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}

	public static final <L extends Listener> void register(final L listener)
	{
		for(Resource<? super L> resource: Resources.registerable((Class<L>) listener.getClass()))
		{
			resource.register(listener);
		}
	}

	public static final <L extends Listener> void unregister(final L listener)
	{
		for(Resource<? super L> resource: Resources.registerable((Class<L>) listener.getClass()))
		{
			resource.unregister(listener);
		}
	}

	public static final Collection<Listener> registered()
	{
		return registered(Listener.class);
	}
	
	public static final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		List<L> listeners = Lists.newArrayList();
		
		for (Resource<? extends L> resource: Resources.assignable(type))
		{
			listeners.addAll(resource.registered(type));
		}
		
		return listeners;
	}

	public static final Map<Resource<?>, Collection<Listener>> registeredMap()
	{
		Map<Resource<?>, Collection<Listener>> map = Maps.newHashMap();
		
		for (Resource<?> resource: Resources.registered())
		{
			map.put(resource, resource.registered(Listener.class));
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
