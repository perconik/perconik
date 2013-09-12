package sk.stuba.fiit.perconik.utilities.reflection;

import java.util.List;
import java.util.Set;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public final class MoreReflection
{
	private MoreReflection()
	{
		throw new AssertionError();
	}
	
	public static final <T> List<Class<? super T>> getSuperclasses(Class<T> type)
	{
		List<Class<? super T>> superclasses = Lists.newLinkedList();
		
		Class<? super T> supertype = type;
		
		while ((supertype = supertype.getSuperclass()) != null)
		{
			superclasses.add(supertype);
		}
			
		return superclasses;
	}
	
	public static final Set<Class<?>> getInterfaces(Class<?> type)
	{
		Set<Class<?>> resolved   = Sets.newHashSet();
		Set<Class<?>> interfaces = Sets.newHashSet(type.getInterfaces());

		resolved.add(type);
		
		for (Class<?> supertype: getSuperclasses(type))
		{
			collectInterfaces(supertype, resolved, interfaces);
		}
		
		return interfaces;
	}
	
	private static final void collectInterfaces(Class<?> type, Set<Class<?>> resolved, Set<Class<?>> result)
	{
		if (resolved.add(type))
		{
			for (Class<?> supertype: type.getInterfaces())
			{
				collectInterfaces(supertype, resolved, result);
			}
		}
		
		result.add(type);
	}
}
