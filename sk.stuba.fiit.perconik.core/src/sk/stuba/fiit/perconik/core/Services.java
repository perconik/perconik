package sk.stuba.fiit.perconik.core;

import sk.stuba.fiit.perconik.core.services.ListenerService;
import sk.stuba.fiit.perconik.core.services.ResourceService;

public final class Services
{
	private Services()
	{
		throw new AssertionError();
	}
	
	public static final void setResourceService(final ResourceService service)
	{
		Internals.setApi(ResourceService.class, service);
	}

	public static final void setListenerService(final ListenerService service)
	{
		Internals.setApi(ListenerService.class, service);
	}

	public static final ResourceService getResourceService()
	{
		return Internals.getApi(ResourceService.class);
	}

	public static final ListenerService getListenerService()
	{
		return Internals.getApi(ListenerService.class);
	}
}