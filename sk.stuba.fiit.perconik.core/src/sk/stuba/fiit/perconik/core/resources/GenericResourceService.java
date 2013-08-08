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
		this.name      = this.name();
		this.resources = Multimaps.synchronizedSetMultimap(HashMultimap.<Class<?>, Resource<?>>create());
	}
	
	private final String name()
	{
		return this.getClass().getCanonicalName();
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

	public final <T extends Listener> Set<Resource<? super T>> registerable(final Class<T> type)
	{
		Set<Resource<? super T>> result = Sets.newHashSet();
		
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
				result.add((Resource<? super T>) entry.getValue());
			}
		}
		
		return result;
	}

	public final <T extends Listener> Set<Resource<? extends T>> assignable(final Class<T> type)
	{
		Set<Resource<? extends T>> result = Sets.newHashSet();
		
		for (Entry<Class<?>, Resource<?>> entry: this.resources.entries())
		{
			boolean matched = type.isAssignableFrom(entry.getKey());

// TODO improve matching
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
				result.add((Resource<? extends T>) entry.getValue());
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
