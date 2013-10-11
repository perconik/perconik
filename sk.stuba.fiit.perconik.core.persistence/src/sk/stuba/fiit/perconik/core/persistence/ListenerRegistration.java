package sk.stuba.fiit.perconik.core.persistence;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listener;

/**
 * Current registration status of a {@code Listener} instance.
 * The current registration status is obtained from the underlying listener.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerRegistration extends AnnotableRegistration
{
	/**
	 * Returns the underlying listener.
	 */
	public Listener getListener();
	
	/**
	 * Returns the underlying listener implementation class.
	 */
	public Class<? extends Listener> getListenerClass();
	
	/**
	 * Compares the specified object with this listener registration
	 * for equality. Returns {@code true} if the specified object is also
	 * a listener registration and the two registrations have the same listener
	 * implementation class. This definition ensures that this method works
	 * properly across different implementations of the listener registration
	 * interface.
	 * @param o an object to be compared for equality
	 *          with this listener registration
	 * @return {@code true} if the specified object is equal to
	 *         this listener registration, {@code false} otherwise
	 */
	@Override
	public boolean equals(@Nullable Object o);
	
    /**
     * Returns the hash code value for this listener registration.
     * The hash code of a listener registration should be equivalent
     * to the result of {@link java.util.Objects#hash(Object...)} for
     * listener implementation class.
     * @return the hash code value for this listener registration
     */
	@Override
	public int hashCode();
}
