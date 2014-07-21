package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Collections;
import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

final class SystemResourceProvider extends AbstractResourceProvider
{
	private static final ResourceProvider instance = new SystemResourceProvider();
	
	private SystemResourceProvider()
	{
	}
	
	static final ResourceProvider getInstance()
	{
		return instance;
	}

	public final Resource<?> forName(final String name)
	{
		return null;
	}

	public final <L extends Listener> Set<Resource<L>> forType(final Class<L> type)
	{
		return Collections.emptySet();
	}

	public final Set<String> names()
	{
		return Collections.emptySet();
	}

	public final Set<Class<? extends Listener>> types()
	{
		return Collections.emptySet();
	}

	public final ResourceProvider parent()
	{
		return null;
	}
}
