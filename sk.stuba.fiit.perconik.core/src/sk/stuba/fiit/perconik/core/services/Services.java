package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;

public final class Services
{
	private Services()
	{
		throw new AssertionError();
	}
	
	public static final void start()
	{
		getResourceService().start();
		getListenerService().start();
	}
	
	public static final void stop()
	{
		getListenerService().stop();
		getResourceService().stop();
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
