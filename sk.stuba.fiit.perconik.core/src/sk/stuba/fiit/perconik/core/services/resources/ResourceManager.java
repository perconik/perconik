package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceAlreadyRegistredException;
import sk.stuba.fiit.perconik.core.ResourceNotRegistredException;
import sk.stuba.fiit.perconik.core.ResourceRegistrationException;
import sk.stuba.fiit.perconik.core.ResourceUnregistrationException;
import sk.stuba.fiit.perconik.core.services.Manager;

/**
 * An object responsible for managing registrations of {@link Resource}
 * instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceManager extends Manager
{
	/**
	 * Registers a resource with the given listener type.
	 * Resource registration hooks are properly invoked during
	 * the registration process.
	 * 
	 * <p>Note that this method propagates any exception
	 * thrown by the specified resource as a cause of a
	 * {@code ResourceRegistrationException} instance.
	 * 
	 * @param type the listener type with which the specified
	 *             resource is to be registered, not {@code null}
	 * @param resource the resource to be registered with the specified
	 *                 listener type, not {@code null}
	 * 
	 * @throws IllegalArgumentException if the specified resource
	 *         implementation does not support the specified listener type
	 * @throws ResourceAlreadyRegistredException if the specified listener
	 *         type and resource pair is already registered and this resource
	 *         manager panics
	 * @throws ResourceRegistrationException if an exception is thrown
	 *         by the specified resource during the registration process 
	 * @throws NullPointerException if either of listener type or resource
	 *         is {@code null}
	 */
	public <L extends Listener> void register(Class<L> type, Resource<? super L> resource);
	
	/**
	 * Unregisters a resource with the given listener type.
	 * Resource unregistration hooks are properly invoked during
	 * the unregistration process.
	 * 
	 * <p>Note that this method propagates any exception
	 * thrown by the specified resource as a cause of a
	 * {@code ResourceUnregistrationException} instance.
	 * 
	 * @param type the listener type with which the specified
	 *             resource is to be unregistered, not {@code null}
	 * @param resource the resource to be unregistered from the specified
	 *                 listener type, not {@code null}
	 * 
	 * @throws IllegalArgumentException if the specified resource
	 *         implementation does not support the specified listener type
	 * @throws ResourceNotRegistredException if the specified listener
	 *         type and resource pair is not registered and this resource
	 *         manager panics
	 * @throws ResourceUnregistrationException if an exception is thrown
	 *         by the specified resource during the unregistration process 
	 * @throws NullPointerException if either of listener type or resource
	 *         is {@code null}
	 */
	public <L extends Listener> void unregister(Class<L> type, Resource<? super L> resource);
	
	/**
	 * Unregisters all resources assignable to the specified listener type.
	 * A resource is assignable to a given listener type if the listener type
	 * supported by the resource is assignable to the given listener type.
	 * Resource unregistration hooks are properly invoked for all matched
	 * resources during the unregistration process.
	 * 
	 * <p>Note that this method collects all exceptions thrown by resources
	 * during the unregistration process and propagates them as suppressed
	 * exceptions of a {@code ResourceUnregistrationException} instance.
	 * 
	 * @param type the listener type to which the assignable resources
	 *             are going be unregistered, not {@code null}
	 * 
	 * @throws ResourceUnregistrationException if an exception is thrown
	 *         by one or more resources during the unregistration process 
	 * @throws NullPointerException if the specified listener
	 *         type is {@code null}
	 */
	public <L extends Listener> void unregisterAll(Class<L> type);
	
	/**
	 * Returns a set of resources assignable to the specified listener type.
	 * 
	 * <p>A resource is assignable to a given listener type if the listener
	 * type supported by the resource is assignable to the given listener type.
	 */
	public <L extends Listener> Set<Resource<? extends L>> assignables(Class<L> type);
	
	/**
	 * Returns a set of resources registrable with the specified listener type.
	 * 
	 * <p>A resource is registrable with a given listener type if the listener
	 * type supported by the resource is equal to the given listener type or
	 * one of its superinterfaces.
	 */
	public <L extends Listener> Set<Resource<? super L>> registrables(Class<L> type);
	
	/**
	 * Returns a set multimap of listener types to all registered resources.
	 */
	public SetMultimap<Class<? extends Listener>, Resource<?>> registrations();
	
	/**
	 * Determines whether the specified resource is registered
	 * with the specified listener type by this resource manager.
	 * 
	 * @param type the listener type to be checked, not {@code null}
	 * @param resource the resource to be checked, not {@code null}
	 * @return {@code true} if the specified resource is registered
	 *         with the specified listener type by this resource manager,
	 *         {@code false} otherwise
	 * 
	 * @throws NullPointerException if either of listener type or resource
	 *         is {@code null}
	 */
	public boolean registered(Class<? extends Listener> type, Resource<?> resource);
	
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
