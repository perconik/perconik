package sk.stuba.fiit.perconik.listeners;

import java.util.Map.Entry;
import java.util.Set;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

public final class Resources
{
//	private static final TypeToken<Resource<LaunchListener>> launch = new TypeToken<Resource<LaunchListener>>() {};
//	
//	private static final TypeToInstanceMap<Resource<?>> resources;
//	
//	static
//	{
//		Builder<Resource<?>> builder = ImmutableTypeToInstanceMap.builder();
//		
//		// TODO
//		builder.put(launch, create(LaunchHandler.INSTANCE));
//		
//		resources = builder.build();
//	}
	
	private static final SetMultimap<Class<?>, Resource<?>> resources = Multimaps.synchronizedSetMultimap(HashMultimap.<Class<?>, Resource<?>>create());
	
	private Resources()
	{
		throw new AssertionError();
	}
	
	static final <T extends Listener> Resource<T> create(final Handler<T> handler)
	{
		return new GenericResource<>(Pools.getPoolFactory().create(handler));
	}

	public static final <T extends Listener> void register(final Class<T> type, final Resource<T> resource)
	{
		resources.put(Preconditions.checkNotNull(type), Preconditions.checkNotNull(resource));
	}
	
	public static final <T extends Listener> void unregister(final Class<T> type, final Resource<T> resource)
	{
		resources.remove(Preconditions.checkNotNull(type), Preconditions.checkNotNull(resource));
	}
	
	public static final <T extends Listener> Set<Resource<? extends T>> forListener(final Class<T> type)
	{
		Set<Resource<? extends T>> result = Sets.newHashSet();
		
		for (Entry<Class<?>, Resource<?>> entry: resources.entries())
		{
			if (type.isAssignableFrom(entry.getKey()))
			{
				result.add((Resource<? extends T>) entry.getValue());
			}
		}
		
		return result;
	}

	public static final Set<Resource<?>> getResources()
	{
		return Sets.newHashSet(resources.values());
	}
		
	// TODO rm
//	public static final <T extends Resource<?>> T getResource(final TypeToken<T> type)
//	{
//		return (T) resources.get(type);
//	}
	
	public static final Resource<LaunchListener> getLaunchResource()
	{
		return KnownResources.launch;
	}
}
