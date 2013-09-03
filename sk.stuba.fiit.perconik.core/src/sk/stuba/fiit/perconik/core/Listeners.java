package sk.stuba.fiit.perconik.core;

import java.util.Arrays;
import java.util.Collection;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import com.google.common.collect.SetMultimap;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}

	static final ListenerService service()
	{
		return Services.getListenerService();
	}
	
	static final ListenerProvider provider()
	{
		return service().getListenerProvider();
	}
	
	static final ListenerManager manager()
	{
		return service().getListenerManager();
	}
	
	public static final void register(final Listener listener)
	{
		manager().register(listener);
	}

	public static final void registerAll(final Listener ... listeners)
	{
		registerAll(Arrays.asList(listeners));
	}
	
	public static final void registerAll(final Iterable<? extends Listener> listeners)
	{
		ListenerManager manager = manager();
		
		for (Listener listener: listeners)
		{
			manager.register(listener);
		}
	}
	
	public static final void registerAll(final ListenerClassesSupplier supplier)
	{
		ListenerProvider provider = provider();
		ListenerManager  manager  = manager();
		
		for (Class<? extends Listener> implementation: supplier.get())
		{
			manager.register(provider.forClass(implementation));
		}
	}

	public static final void unregister(final Listener listener)
	{
		manager().unregister(listener);
	}

	public static final void unregisterAll()
	{
		unregisterAll(Listener.class);
	}

	public static final void unregisterAll(final Class<? extends Listener> type)
	{
		manager().unregisterAll(type);
	}

	public static final Collection<Listener> registered()
	{
		return registered(Listener.class);
	}
	
	public static final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		return manager().registered(type);
	}

	public static final SetMultimap<Resource<?>, Listener> registrations()
	{
		return manager().registrations();
	}

	public static final boolean isRegistered(final Class<? extends Listener> type)
	{
		// TODO extend manager interface
		
		for (Listener listener: registrations().values())
		{
			if (type.isInstance(listener))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static final boolean isRegistered(final Listener listener)
	{
		return registrations().containsValue(listener);
	}
}
