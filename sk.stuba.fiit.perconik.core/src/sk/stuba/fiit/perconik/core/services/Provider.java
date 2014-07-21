package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.Nameable;

/**
 * An object providing other objects, usually {@code Registrable} instances.
 * 
 * <p>Uniqueness of a provider is determined by its qualified name.
 * Two {@code Provider} instances with the same qualified name should
 * be implemented by the same class, provide the same functionality
 * and behave the same way.
 * 
 * <p>All providers should fall back to their parent ask it to provide
 * requested objects if they are themselves unable to provide these objects,
 * with exception for the providers at the top of the provider hierarchy which
 * have no parent. 
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Provider extends Nameable
{
	/**
	 * Returns the provider's parent or {@code null} if there is no parent.
	 */
	public Provider parent();
	
	/**
	 * Compares the specified object with this provider for equality.
	 * Returns {@code true} if the specified object is also provider
	 * and the two providers have the same name. This definition ensures
	 * that this method works properly across different implementations
	 * of the provider interface.
	 * @param o an object to be compared for equality with this provider
	 * @return {@code true} if the specified object is equal to
	 *         this provider, {@code false} otherwise
	 */
	@Override
	public boolean equals(@Nullable Object o);
	
    /**
     * Returns the hash code value for this provider. The hash code of a
     * provider should be equivalent to the hash code of the provider's name.
     * @return the hash code value for this provider
     */
	@Override
	public int hashCode();
}
