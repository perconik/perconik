package sk.stuba.fiit.perconik.core;

import java.util.Collection;
import java.util.Map;
import sk.stuba.fiit.perconik.core.listeners.DefaultListeners;
import sk.stuba.fiit.perconik.core.services.ListenerService;
import com.google.common.base.Preconditions;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}

	private static enum ServiceAccessor
	{
		INSTANCE;
		
		private ListenerService service;
		
		final synchronized void set(final ListenerService service)
		{
			this.service = Preconditions.checkNotNull(service);
		}
		
		final synchronized ListenerService get()
		{
			if (this.service != null)
			{
				return this.service;
			}
			
			return DefaultListeners.getDefaultListenerService();
		}
	}
	
	public static final void setListenerService(final ListenerService service)
	{
		ServiceAccessor.INSTANCE.set(service);
	}
	
	public static final ListenerService getListenerService()
	{
		return ServiceAccessor.INSTANCE.get();
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
}
