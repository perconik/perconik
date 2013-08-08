package sk.stuba.fiit.perconik.core.resources;

import java.util.Map.Entry;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceService;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

final class GenericResourceService implements ResourceService
{
	private final String name;
	
	private final SetMultimap<Class<?>, Resource<?>> resources;
	
	GenericResourceService()
	{
		this.name      = name(this.getClass());
		this.resources = Multimaps.synchronizedSetMultimap(HashMultimap.<Class<?>, Resource<?>>create());
	}
	
	private static final String name(final Class<?> type)
	{
		return type.getClass().getCanonicalName();
	}
	
	public final <L extends Listener> void register(final Class<L> type, final Resource<L> resource)
	{
		Preconditions.checkNotNull(type);
		Preconditions.checkNotNull(resource);
		
		this.resources.put(type, resource);
	}
	
	public final <L extends Listener> void unregister(final Class<L> type, final Resource<L> resource)
	{
		resource.unregisterAll(type);
		
		this.resources.remove(type, resource);
	}
		
	public final Set<Resource<?>> registered()
	{
		return Sets.newHashSet(this.resources.values());
	}

	public final <L extends Listener> Set<Resource<? super L>> registerable(final Class<L> type)
	{
		Set<Resource<? super L>> result = Sets.newHashSet();
		
		for (Entry<Class<?>, Resource<?>> entry: this.resources.entries())
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

	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		Set<Resource<? extends L>> result = Sets.newHashSet();
		
		for (Entry<Class<?>, Resource<?>> entry: this.resources.entries())
		{
			boolean matched = type.isAssignableFrom(entry.getKey());

// LODO improve matching
//
//			if (!matched)
//			{
//				for (Class<?> supertype: type.getInterfaces())
//				{
//					if (supertype == entry.getKey())
//					{
//						matched = true;
//						
//						break;
//					}
//				}
//			}

//			System.out.println("--------");
//			System.out.println(Resources.registerable(Listener.class));
//			System.out.println(Resources.registerable(DebugListener.class));
//			System.out.println(Resources.registerable(LaunchListener.class));
//			System.out.println(Resources.registerable(LaunchDebugListener.class));
//			System.out.println("--------");
//			System.out.println(Resources.assignable(Listener.class));
//			System.out.println(Resources.assignable(DebugListener.class));
//			System.out.println(Resources.assignable(LaunchListener.class));
//			System.out.println(Resources.assignable(LaunchDebugListener.class));
//			System.out.println("--------");
//			System.exit(1);
			
			if (matched)
			{
				result.add((Resource<? extends L>) entry.getValue());
			}
		}
		
		return result;
	}

	@Override
	public final String toString()
	{
		return this.getName();
	}

	public final String getName()
	{
		return this.name;
	}
}
