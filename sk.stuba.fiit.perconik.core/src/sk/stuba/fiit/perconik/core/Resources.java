package sk.stuba.fiit.perconik.core;

import java.util.Set;
import sk.stuba.fiit.perconik.core.services.ResourceProvider;
import sk.stuba.fiit.perconik.core.services.ResourceService;

public final class Resources
{
	private Resources()
	{
		throw new AssertionError();
	}

	public static final <L extends Listener> void register(Class<L> type, Resource<L> resource)
	{
		getResourceService().register(type, resource);
	}

	public static final <L extends Listener> void unregister(Class<L> type, Resource<L> resource)
	{
		getResourceService().unregister(type, resource);
	}

	public static final Set<Resource<?>> registered()
	{
		return getResourceService().registered();
	}

	public static final <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type)
	{
		return getResourceService().assignable(type);
	}

	public static final <L extends Listener> Set<Resource<? super L>> registerable(Class<L> type)
	{
		return getResourceService().registerable(type);
	}

	public static final void setResourceService(final ResourceService service)
	{
		Internals.setApi(ResourceService.class, service);
	}

	public static final void setResourceProvider(final ResourceProvider provider)
	{
		Internals.setApi(ResourceProvider.class, provider);
	}

	public static final ResourceService getResourceService()
	{
		return Internals.getApi(ResourceService.class);
	}

	public static final ResourceProvider getResourceProvider()
	{
		return Internals.getApi(ResourceProvider.class);
	}
}
