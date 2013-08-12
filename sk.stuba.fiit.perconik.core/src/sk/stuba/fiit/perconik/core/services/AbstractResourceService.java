package sk.stuba.fiit.perconik.core.services;

import java.util.Map.Entry;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public abstract class AbstractResourceService implements ResourceService
{
	protected AbstractResourceService()
	{
	}
	
	protected abstract <L extends Listener> boolean put(final Class<L> type, final Resource<L> resource);
	
	protected abstract <L extends Listener> boolean remove(final Class<L> type, final Resource<L> resource);
	
	protected abstract Set<Entry<Class<? extends Listener>, Resource<?>>> entries();
	
	public final <L extends Listener> void register(final Class<L> type, final Resource<L> resource)
	{
		Preconditions.checkNotNull(type);
		Preconditions.checkNotNull(resource);
		
		this.put(type, resource);
	}
	
	public final <L extends Listener> void unregister(final Class<L> type, final Resource<L> resource)
	{
		resource.unregisterAll(type);
		
		this.remove(type, resource);
	}
		
	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
		{
			Set<Resource<? extends L>> result = Sets.newHashSet();
			
			for (Entry<Class<? extends Listener>, Resource<?>> entry: this.entries())
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
					result.add((Resource<? extends L>) entry.getValue());
				}
			}
			
			return result;
		}

	public final <L extends Listener> Set<Resource<? super L>> registerable(final Class<L> type)
	{
		Set<Resource<? super L>> result = Sets.newHashSet();
		
		for (Entry<Class<? extends Listener>, Resource<?>> entry: this.entries())
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
}
