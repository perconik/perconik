package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ListenerAlreadyRegistredException;
import sk.stuba.fiit.perconik.core.ListenerNotRegistredException;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.Manager;
import com.google.common.collect.SetMultimap;

/**
 * An object responsible for managing registrations of {@link Listener}
 * instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerManager extends Manager
{
	/**
	 * Registers a listener to all compatible resources.
	 * 
	 * @param listener the listener to be registered, not {@code null}
	 * 
	 * @throws ListenerAlreadyRegistredException if the specified listener
	 *         is already registered and this listener manager panics
	 * @throws NullPointerException if the specified listener is {@code null}
	 */
	public <L extends Listener> void register(final L listener);

	/**
	 * Unregisters a listener from all compatible resources.
	 * 
	 * @param listener the listener to be unregistered, not {@code null}
	 * 
	 * @throws ListenerNotRegistredException if the specified listener
	 *         is not registered and this listener manager panics
	 * @throws NullPointerException if the specified listener is {@code null}
	 */
	public <L extends Listener> void unregister(final L listener);
	
	/**
	 * Unregisters all listeners assignable to the specified listener type
	 * from all compatible resources. A listener is assignable to a listener
	 * type if it is an instance of that type.
	 * 
	 * @param type the listener type to which the assignable listeners
	 *             are going be unregistered, not {@code null}
	 * 
	 * @throws NullPointerException if the specified listener
	 *         type is {@code null}
	 */
	public <L extends Listener> void unregisterAll(final Class<L> type);

	/**
	 * Returns a set multimap of resources to all registered listeners.
	 */
	public SetMultimap<Resource<?>, Listener> registrations();
	
	/**
	 * Returns all registered listeners assignable to the specified listener
	 * type from all compatible resources. A listener is assignable to a
	 * listener type if it is an instance of that type.
	 * 
	 * @param type the listener type of the registered listeners,
	 *             not {@code null}
	 * 
	 * @throws NullPointerException if the specified listener
	 *         type is {@code null}
	 */
	public <U extends Listener> Collection<U> registered(final Class<U> type);
	
	/**
	 * Determines whether the specified listener
	 * is registered to all compatible resources.
	 * 
	 * @param listener the listener to be checked, not {@code null}
	 * @return {@code true} if the specified listener is registered
	 *         to all compatible resources, {@code false} otherwise
	 * 
	 * @throws NullPointerException if the specified listener is {@code null}
	 */
	public boolean registered(Listener listener);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(@Nullable Object o);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode();
}
