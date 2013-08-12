package sk.stuba.fiit.perconik.core;

import java.util.Collection;
import java.util.Map;
import sk.stuba.fiit.perconik.core.services.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.ListenerService;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}

	public static final <L extends Listener> void register(final L listener)
	{
		getListenerService().register(listener);
	}

	public static final <L extends Listener> void unregister(final L listener)
	{
		getListenerService().unregister(listener);
	}

	public static final void unregisterAll()
	{
		unregisterAll(Listener.class);
	}

	public static final void unregisterAll(final Class<? extends Listener> type)
	{
		getListenerService().unregisterAll(type);
	}

	public static final Collection<Listener> registered()
	{
		return registered(Listener.class);
	}
	
	public static final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		return getListenerService().registered(type);
	}

	public static final Map<Resource<?>, Collection<Listener>> registrations()
	{
		return getListenerService().registrations();
	}

	public static final void setListenerService(final ListenerService service)
	{
		Internals.setApi(ListenerService.class, service);
	}

	public static final void setListenerProvider(final ListenerProvider provider)
	{
		Internals.setApi(ListenerProvider.class, provider);
	}

	public static final ListenerService getListenerService()
	{
		return Internals.getApi(ListenerService.class);
	}

	public static final ListenerProvider getListenerProvider()
	{
		return Internals.getApi(ListenerProvider.class);
	}
}
