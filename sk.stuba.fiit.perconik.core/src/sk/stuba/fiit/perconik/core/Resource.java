package sk.stuba.fiit.perconik.core;

import java.util.Collection;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;

/**
 * A resource for managing registrable {@link Listener} instances. In general
 * a resource implements listener registration mechanism as well as other
 * supporting utilities for specific listener type. Resource may be also
 * a source of listener events or act as a bridge between the event source
 * and the listeners.
 * 
 * <p>Each resource is identified by its name returned by {@link #getName()}
 * method. Resource names should be fully qualified and unique for different
 * resource implementations as resources with the same names are considered
 * equal by the {@link #equals(Object)} method.
 * 
 * <p>A resource must use {@link Listener#equals} to determine whether two
 * listeners should be considered as the same. Similarly a resource should
 * always check if it supports the encountered listener type and appropriately
 * throw a {@code ClassCastException}. Resource does not permit {@code null}
 * listeners.
 * 
 * @param <L> the type of listeners compatible with this resource
 * 
 * @see Listener
 * @see Resources
 * @see DefaultResources
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Resource<L extends Listener> extends Nameable, Registrable
{
	/**
	 * Registers a listener to this resource.
	 * 
	 * <p><b>Warning:</b> Does not invoke listener registration hooks.
	 * Only {@link ListenerManager#register(Listener)} is responsible
	 * for proper hook method invocation during the registration process.
	 * 
	 * @param listener the listener to be registered, not {@code null}
	 * 
     * @throws ClassCastException if the type of the specified listener
     *         prevents it from being registered to this resource
	 * @throws ListenerAlreadyRegistredException if the specified listener
	 *         is already registered and this resource panics
	 * @throws NullPointerException if the specified listener is {@code null}
	 * @throws UnsupportedResourceException if the resource does not support
	 *         this operation (it could mean that it is not implemented yet)
	 */
	public void register(L listener);
	
	/**
	 * Unregisters a listener from this resource.
	 * 
	 * <p><b>Warning:</b> Does not invoke listener unregistration hooks.
	 * Only {@link ListenerManager#unregister(Listener)} is responsible
	 * for proper hook method invocation during the unregistration process.
	 * 
	 * @param listener the listener to be unregistered, not {@code null}
	 * 
     * @throws ClassCastException if the type of the specified listener
     *         prevents it from being unregistered from this resource
	 * @throws ListenerNotRegistredException if the specified listener
	 *         is not registered and this resource panics
	 * @throws NullPointerException if the specified listener is {@code null}
	 * @throws UnsupportedResourceException if the resource does not support
	 *         this operation (it could mean that it is not implemented yet)
	 */
	public void unregister(L listener);

	// TODO rm
//	/**
//	 * Unregisters all listeners assignable to the specified listener type.
//	 * A listener is assignable to a listener type if it is an instance of
//	 * that type.
//	 * 
//	 * <p><b>Warning:</b> Does not invoke unregistration hooks of assignable
//	 * listener. Only {@link ListenerManager#register(Listener)} is responsible
//	 * for proper hook method invocation during the unregistration process.
//	 * 
//	 * @param type the listener type to which the assignable listeners
//	 *             are going be unregistered, not {@code null}
//	 * 
//	 * @throws NullPointerException if the specified listener
//	 *         type is {@code null}
//	 * @throws UnsupportedResourceException if the resource does not support
//	 *         this operation (it could mean that it is not implemented yet)
//	 */
//	public void unregisterAll(Class<? extends Listener> type);
	
	/**
	 * Returns all registered listeners assignable to the specified
	 * listener type. A listener is assignable to a listener type
	 * if it is an instance of that type.
	 * 
	 * @param type the listener type of the registered listeners,
	 *             not {@code null}
	 * 
	 * @throws NullPointerException if the specified listener
	 *         type is {@code null}
	 */
	public <U extends Listener> Collection<U> registered(Class<U> type);
	
	/**
	 * Determines whether the specified listener
	 * is registered to this resource.
	 * 
	 * @param listener the listener to be checked, not {@code null}
	 * @return {@code true} if the specified listener is registered
	 *         to this resource, {@code false} otherwise
	 * 
	 * @throws NullPointerException if the specified listener is {@code null}
	 */
	public boolean registered(Listener listener);
	
	/**
	 * Compares the specified object with this resource for equality.
	 * Returns {@code true} if the specified object is also resource
	 * and the two resources have the same name. This definition ensures
	 * that this method works properly across different implementations
	 * of the resource interface.
	 * 
	 * @param o an object to be compared for equality with this resource
	 * @return {@code true} if the specified object is equal to
	 *         this resource, {@code false} otherwise
	 */
	@Override
	public boolean equals(@Nullable Object o);
	
    /**
     * Returns the hash code value for this resource. The hash code of a
     * resource should be equivalent to the hash code of the resource's name.
     * 
     * @return the hash code value for this resource
     */
	@Override
	public int hashCode();
	
	/**
	 * Returns a fully qualified name of this resource.
	 */
	@Override
	public String getName();
}
