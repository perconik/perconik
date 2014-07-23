package sk.stuba.fiit.perconik.utilities.reflect.resolver;

import com.google.common.base.Preconditions;

final class LoadingClassResolver implements ClassResolver
{
	private final ClassLoader loader;
	
	LoadingClassResolver(ClassLoader loader)
	{
		this.loader = Preconditions.checkNotNull(loader);
	}

	public final Class<?> forName(String name) throws ClassNotFoundException
	{
		return this.loader.loadClass(name);
	}

	@Override
	public final String toString()
	{
		return "LoadingClassResolver(" + this.loader + ")";
	}
}
