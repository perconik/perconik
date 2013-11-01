package sk.stuba.fiit.perconik.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import sk.stuba.fiit.perconik.core.annotations.Internal;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import sk.stuba.fiit.perconik.utilities.reflect.Reflections;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

/**
 * Static accessor methods pertaining to the listeners core. 
 * 
 * <p>This class provides access to the underlying functionality
 * of the currently active {@code ListenerService}.
 * 
 * @see Listener
 * @see Resources
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Listeners
{
	// TODO add javadocs
	
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
	
	public static final Listener forClass(final Class<? extends Listener> type)
	{
		return provider().forClass(type);
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
		List<Exception> failures = Lists.newLinkedList();
		
		ListenerManager manager = manager();
		
		for (Listener listener: listeners)
		{
			try
			{
				manager.register(listener);
			}
			catch (Exception failure)
			{
				failures.add(failure);
			}
		}
		
		if (!failures.isEmpty())
		{
			throw MoreThrowables.initializeSuppressor(new ListenerRegistrationException(), failures);
		}
	}
	
	public static final void registerAll(final ListenerClassesSupplier supplier)
	{
		List<Exception> failures = Lists.newLinkedList();
		
		ListenerProvider provider = provider();
		ListenerManager  manager  = manager();
		
		for (Class<? extends Listener> implementation: supplier.get())
		{
			Listener listener = provider.forClass(implementation);
			
			try
			{
				manager.register(listener);
			}
			catch (Exception failure)
			{
				failures.add(failure);
			}
		}
		
		if (!failures.isEmpty())
		{
			throw MoreThrowables.initializeSuppressor(new ListenerRegistrationException(), failures);
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

	public static final boolean isRegistered(final Listener listener)
	{
		return manager().registered(listener);
	}
	
	public static final Set<Class<? extends Listener>> resolveTypes(final Listener listener)
	{
		Set<Class<?>> raw = Reflections.collectInterfaces(listener.getClass());

		raw.remove(Registrable.class);
		raw.remove(Listener.class);
		
		Set<Class<? extends Listener>> types = Sets.newHashSetWithExpectedSize(raw.size());
		
		for (Class<?> type: raw)
		{
			if (Listener.class.isAssignableFrom(type) && !type.isAnnotationPresent(Internal.class))
			{
				types.add(type.asSubclass(Listener.class));
			}
		}
		
		Iterable<Class<? extends Listener>> iterable = Lists.newArrayList(types);
		
		for (Class<?> a: iterable)
		{
			for (Class<?> b: iterable)
			{
				if (a != b && a.isAssignableFrom(b))
				{
					types.remove(a);
				}
			}
		}
		
		return types;
	}
}
