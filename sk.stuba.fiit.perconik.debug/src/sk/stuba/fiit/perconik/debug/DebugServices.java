package sk.stuba.fiit.perconik.debug;

import sk.stuba.fiit.perconik.core.Services;
import sk.stuba.fiit.perconik.debug.services.DebugListenerServiceWrapper;
import sk.stuba.fiit.perconik.debug.services.DebugResourceServiceWrapper;

public final class DebugServices
{
	private DebugServices()
	{
		throw new AssertionError();
	}
	
	public static final void wrapAll()
	{
		wrapResourceService();
		wrapListenerService();
	}

	public static final void wrapResourceService()
	{
		Services.setResourceService(DebugResourceServiceWrapper.of(Services.getResourceService()));
	}

	public static final void wrapListenerService()
	{
		Services.setListenerService(DebugListenerServiceWrapper.of(Services.getListenerService()));
	}
}
