package sk.stuba.fiit.perconik.utilities.reflection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class ClassResolvers
{
	private ClassResolvers()
	{
		throw new AssertionError();		
	}

	public static final ClassResolver forClassLoader(ClassLoader loader)
	{
		return new LoadingClassResolver(loader);
	}
	
	public static final ClassResolver forSystemClassLoader()
	{
		return forClassLoader(ClassLoader.getSystemClassLoader());
	}

	public static final ClassResolver forClass(Class<?> type)
	{
		return forClassLoader(type.getClassLoader());
	}
	
	public static final ClassResolver forObject(Object object)
	{
		return forClass(object.getClass());
	}

	public static final ClassResolver forThread(Thread thread)
	{
		return forClassLoader(thread.getContextClassLoader());
	}
	
	public static final ClassResolver forCurrentThread()
	{
		return forThread(Thread.currentThread());
	}

	public static final ClassResolver getDefault()
	{
		return DefaultClassResolver.INSTANCE;
	}
	
	public static final ClassResolver compose(ClassResolver a, ClassResolver b)
	{
		return compose(ImmutableList.of(a, b));
	}

	public static final ClassResolver compose(ClassResolver a, ClassResolver b, ClassResolver ... rest)
	{
		return compose(Lists.asList(a, b, rest));
	}
	
	public static final ClassResolver compose(Iterable<ClassResolver> resolvers)
	{
		return new CompositeClassResolver(resolvers);
	}
}
