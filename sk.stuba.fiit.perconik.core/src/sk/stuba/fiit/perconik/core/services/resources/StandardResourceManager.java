package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import java.util.Map.Entry;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

final class StandardResourceManager extends AbstractResourceManager
{
	private final SetMultimap<Class<? extends Listener>, Resource<?>> map;
	
	StandardResourceManager()
	{
		this.map = HashMultimap.create();
	}
	
	@Override
	protected final SetMultimap<Class<? extends Listener>, Resource<?>> multimap()
	{
		return this.map;
	}
	
	public final <L extends Listener> void unregisterAll(final Class<L> type)
	{
		for (Entry<Class<? extends L>, Resource<? extends L>> entry: this.assignableInternal(type).entries())
		{
			this.unregister(entry.getKey(), Unsafe.cast(type, entry.getValue()));
		}
	}
	
	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		return Sets.newHashSet(this.assignableInternal(type).values());
	}

	private final <L extends Listener> SetMultimap<Class<? extends L>, Resource<? extends L>> assignableInternal(final Class<L> type)
	{
		SetMultimap<Class<? extends L>, Resource<? extends L>> result = HashMultimap.create();
		
		for (Entry<Class<? extends Listener>, Resource<?>> entry: this.map.entries())
		{
			if (type.isAssignableFrom(entry.getKey()))
			{
				result.put((Class<? extends L>) entry.getKey(), (Resource<? extends L>) entry.getValue());
			}
		}
		
		return result;
	}
	
	public final <L extends Listener> Set<Resource<? super L>> registrable(final Class<L> type)
	{
		Set<Resource<? super L>> result = Sets.newHashSet();
		
		for (Entry<Class<? extends Listener>, Resource<?>> entry: this.map.entries())
		{
			boolean matched = type == entry.getKey();
			
			if (!matched)
			{
				for (Class<?> supertype: type.getInterfaces())
				{
					if (supertype == entry.getKey())
					{
						matched = true;
						
						break;
					}
				}
			}
			
			if (matched)
			{
				result.add((Resource<? super L>) entry.getValue());
			}
		}
		
		return result;
	}

	public final SetMultimap<Class<? extends Listener>, Resource<?>> registrations()
	{
		return HashMultimap.create(this.map);
	}
}
