package sk.stuba.fiit.perconik.core.resources;

import java.util.Map.Entry;
import java.util.Set;
import sk.stuba.fiit.perconik.core.listeners.Listener;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

final class GenericResourceService implements ResourceService
{
	private final SetMultimap<Class<?>, Resource<?>> resources;
	
	GenericResourceService()
	{
		this.resources = Multimaps.synchronizedSetMultimap(HashMultimap.<Class<?>, Resource<?>>create());
	}
	
	public final <T extends Listener> void register(final Class<T> type, final Resource<T> resource)
	{
		this.resources.put(Preconditions.checkNotNull(type), Preconditions.checkNotNull(resource));
	}
	
	public final <T extends Listener> void unregister(final Class<T> type, final Resource<T> resource)
	{
		this.resources.remove(Preconditions.checkNotNull(type), Preconditions.checkNotNull(resource));
	}
		
	public final Set<Resource<?>> registered()
	{
		return Sets.newHashSet(this.resources.values());
	}

	public final <T extends Listener> Set<Resource<? extends T>> forListener(final Class<T> type)
	{
		Set<Resource<? extends T>> result = Sets.newHashSet();
		
		for (Entry<Class<?>, Resource<?>> entry: this.resources.entries())
		{
//			boolean assignable = type.isAssignableFrom(entry.getKey());
//			
//			// TODO reconsider this matching
//			if (assignable == false)
//			{
//				for (Class<?> supertype: type.getInterfaces())
//				{
//					if (supertype == entry.getKey())
//					{
//						assignable = true;
//						
//						break;
//					}
//				}
//			}
			
			if (type.isAssignableFrom(entry.getKey()))
			{
				result.add((Resource<? extends T>) entry.getValue());
			}
		}
		
		return result;
	}
}
