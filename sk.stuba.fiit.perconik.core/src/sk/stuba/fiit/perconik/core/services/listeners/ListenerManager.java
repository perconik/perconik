package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;

import javax.annotation.Nullable;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ListenerAlreadyRegistredException;
import sk.stuba.fiit.perconik.core.ListenerNotRegistredException;
import sk.stuba.fiit.perconik.core.ListenerRegistrationException;
import sk.stuba.fiit.perconik.core.ListenerUnregistrationException;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.Manager;

/**
 * An object responsible for managing registrations of {@link Listener}
 * instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerManager extends Manager {
  /**
   * Registers a listener to all compatible resources.
   * Listener unregistration hooks are properly invoked during
   * the unregistration process.
   * 
   * <p>Note that this method propagates any exception
   * thrown by the specified listener as a cause of a
   * {@code ListenerRegistrationException} instance.
   * 
   * @param listener the listener to be registered, not {@code null}
   * 
   * @throws ListenerAlreadyRegistredException if the specified listener
   *         is already registered and this listener manager panics
   * @throws ListenerRegistrationException if an exception is thrown
   *         by the specified listener during the unregistration process 
   * @throws NullPointerException if the specified listener is {@code null}
   */
  public <L extends Listener> void register(final L listener);

  /**
   * Unregisters a listener from all compatible resources.
   * Listener unregistration hooks are properly invoked during
   * the unregistration process.
   * 
   * <p>Note that this method propagates any exception
   * thrown by the specified listener as a cause of a
   * {@code ListenerUnregistrationException} instance.
   * 
   * @param listener the listener to be unregistered, not {@code null}
   * 
   * @throws ListenerNotRegistredException if the specified listener
   *         is not registered and this listener manager panics
   * @throws ListenerUnregistrationException if an exception is thrown
   *         by the specified listener during the registration process 
   * @throws NullPointerException if the specified listener is {@code null}
   */
  public <L extends Listener> void unregister(final L listener);

  /**
   * Unregisters all listeners assignable to the specified listener type
   * from all compatible resources. A listener is assignable to a listener
   * type if it is an instance of that type. Listener unregistration hooks
   * are properly invoked for all matched listeners during the unregistration
   * process.
   *
   * <p>Note that this method collects all exceptions thrown by listeners
   * during the unregistration process and propagates them as suppressed
   * exceptions of a {@code ListenerUnregistrationException} instance.
   * 
   * @param type the listener type to which the assignable listeners
   *             are going be unregistered, not {@code null}
   * 
   * @throws ListenerUnregistrationException if an exception is thrown
   *         by one or more listeners during the unregistration process
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
