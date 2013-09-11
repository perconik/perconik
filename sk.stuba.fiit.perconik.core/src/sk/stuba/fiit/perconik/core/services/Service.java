package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Nameable;

/**
 * An object with an operational state, plus asynchronous lifecycle methods
 * to transition between states.
 * 
 * <p>Uniqueness of a service is determined by its qualified name.
 * Two {@code Service} instances with the same qualified name should
 * be implemented by the same class, provide the same functionality
 * and behave the same way.
 * 
 * <p>Implementors of this interface are strongly encouraged to extend one
 * of the abstract classes in this package which implement this interface
 * and make the threading and state management easier.
 * 
 * @see com.google.common.util.concurrent.Service
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Service extends Nameable, com.google.common.util.concurrent.Service
{
	/**
	 * Compares the specified object with this service for equality.
	 * Returns {@code true} if the specified object is also service
	 * and the two services have the same name. This definition ensures
	 * that this method works properly across different implementations
	 * of the service interface.
	 * @param o an object to be compared for equality with this service
	 * @return {@code true} if the specified object is equal to
	 *         this service, {@code false} otherwise
	 */
	@Override
	public boolean equals(@Nullable Object o);
	
    /**
     * Returns the hash code value for this service. The hash code of a
     * service should be equivalent to the hash code of the service's name.
     * @return the hash code value for this service
     */
	@Override
	public int hashCode();
}
