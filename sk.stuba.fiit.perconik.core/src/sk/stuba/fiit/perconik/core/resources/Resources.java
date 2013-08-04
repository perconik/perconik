package sk.stuba.fiit.perconik.core.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.listeners.Listener;
import com.google.common.base.Preconditions;

public final class Resources
{
	private static ResourceService service;
	
	private Resources()
	{
		throw new AssertionError();
	}
	
	public static final void setResourceService(final ResourceService service)
	{
		Resources.service = Preconditions.checkNotNull(service);
	}
	
	public static final ResourceService getResourceService()
	{
		return Resources.service;
	}
	
	public static final <T extends Listener> void register(Class<T> type, Resource<T> resource)
	{
		service.register(type, resource);
	}

	public static final <T extends Listener> void unregister(Class<T> type, Resource<T> resource)
	{
		service.unregister(type, resource);
	}

	public static final Set<Resource<?>> registered()
	{
		return service.registered();
	}

	public static final <T extends Listener> Set<Resource<? extends T>> forListener(Class<T> type)
	{
		return service.forListener(type);
	}
}
