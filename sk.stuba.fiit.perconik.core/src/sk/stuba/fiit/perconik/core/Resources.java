package sk.stuba.fiit.perconik.core;

import java.util.Set;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;

public final class Resources
{
	private Resources()
	{
		throw new AssertionError();
	}

	public static final <L extends Listener> void register(Class<L> type, Resource<L> resource)
	{
		Services.getResourceService().getResourceManager().register(type, resource);
	}

	public static final <L extends Listener> void unregister(Class<L> type, Resource<L> resource)
	{
		Services.getResourceService().getResourceManager().unregister(type, resource);
	}

	public static final Set<Resource<?>> registered()
	{
		return Services.getResourceService().getResourceManager().registered();
	}

	public static final <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type)
	{
		return Services.getResourceService().getResourceManager().assignable(type);
	}

	public static final <L extends Listener> Set<Resource<? super L>> registerable(Class<L> type)
	{
		return Services.getResourceService().getResourceManager().registerable(type);
	}
	
	public static final String getName(final Resource<?> resource)
	{
		String name = DefaultResources.getName(resource);
		
		if (name != null)
		{
			return name;
		}
		
		return resource.toString();
	}
}
