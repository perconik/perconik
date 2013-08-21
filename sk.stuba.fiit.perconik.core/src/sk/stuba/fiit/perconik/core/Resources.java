package sk.stuba.fiit.perconik.core;

import java.util.Set;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.Services;
import com.google.common.collect.SetMultimap;

public final class Resources
{
	private Resources()
	{
		throw new AssertionError();
	}

	public static final <L extends Listener> void register(final Class<L> type, final Resource<? super L> resource)
	{
		Services.getResourceService().getResourceManager().register(type, resource);
	}

	public static final <L extends Listener> void unregister(final Class<L> type, final Resource<? super L> resource)
	{
		Services.getResourceService().getResourceManager().unregister(type, resource);
	}
	
	public static final <L extends Listener> void unregisterAll()
	{
		unregisterAll(Listener.class);
	}
	
	public static final <L extends Listener> void unregisterAll(final Class<L> type)
	{
		Services.getResourceService().getResourceManager().unregisterAll(type);
	}

	public static final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		return Services.getResourceService().getResourceManager().assignable(type);
	}

	public static final <L extends Listener> Set<Resource<? super L>> registrable(final Class<L> type)
	{
		return Services.getResourceService().getResourceManager().registrable(type);
	}

	public static final SetMultimap<Class<? extends Listener>, Resource<?>> registrations()
	{
		return Services.getResourceService().getResourceManager().registrations();
	}

	public static final boolean isRegistred(final Class<? extends Listener> type, final Resource<?> resource)
	{
		return registrations().containsEntry(type, resource);
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
