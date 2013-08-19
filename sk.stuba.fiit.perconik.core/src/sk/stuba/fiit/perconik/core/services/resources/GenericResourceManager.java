package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import java.util.Map.Entry;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

final class GenericResourceManager extends AbstractResourceManager
{
	private final SetMultimap<Class<? extends Listener>, Resource<?>> map;
	
	GenericResourceManager()
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
		for (Resource<?> resource: this.multimap().get(type))
		{
			this.unregister(type, Unsafe.cast(type, resource));
		}
	}
	
	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		Set<Resource<? extends L>> result = Sets.newHashSet();
		
		for (Entry<Class<? extends Listener>, Resource<?>> entry: this.map.entries())
		{
			if (type.isAssignableFrom(entry.getKey()))
			{
				result.add((Resource<? extends L>) entry.getValue());
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
