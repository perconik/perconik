package sk.stuba.fiit.perconik.core.resources;

import java.util.Set;
import java.util.Map.Entry;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.AbstractResourceManager;
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
	protected final SetMultimap<Class<? extends Listener>, Resource<?>> map()
	{
		return this.map;
	}
	
	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		Set<Resource<? extends L>> result = Sets.newHashSet();
		
		for (Entry<Class<? extends Listener>, Resource<?>> entry: this.map.entries())
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
