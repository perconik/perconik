package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.IllegalListenerClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ListenerInstantiationException;
import sk.stuba.fiit.perconik.core.services.Provider;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProviders;

/**
 * An object responsible for providing {@link Listener} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerProvider extends Provider
{
	/**
	 * Returns an instance of the specified listener implementation class.
	 * 
	 * <p>This method is guaranteed to return the same listener instance
	 * for the specified implementation class every time it is invoked.
	 * 
	 * @param implementation the listener implementation class
	 * @return the listener
	 * 
	 * @throws NullPointerException if the specified listener implementation
	 *         class is {@code null}
	 * @throws IllegalListenerClassException if the listener implementation
	 *         class is invalid
	 * @throws ListenerInstantiationException if the listener instantiation
	 *         failed
	 */
	public <L extends Listener> L forClass(Class<L> implementation);
	
	/**
	 * Loads a listener implementation class with the specified binary name.
	 * 
	 * @param name the binary name of the listener implementation class,
	 *             not an empty string or {@code null}
	 * @return the listener implementation class
	 * 
	 * @throws IllegalArgumentException if the specified class name
	 *         is an empty string
	 * @throws NullPointerException if the specified class name
	 *         is {@code null}
	 * @throws ClassNotFoundException if the class was not found
	 * @throws IllegalListenerClassException if the listener implementation
	 *         class is invalid
	 */
	public Class<? extends Listener> loadClass(String name) throws ClassNotFoundException;
	
	/**
	 * Returns implementation classes of all provided listeners.
	 */
	public Set<Class<? extends Listener>> classes();
	
	/**
	 * Returns the listener provider's parent or {@code null} if there is no
	 * parent. Standard implementations should return system listener provider
	 * if there is no direct parent (only the system listener provider returns
	 * {@code null} as it is at the top of the listener provider hierarchy).
	 * @see ResourceProviders#getSystemProvider()
	 */
	@Override
	public ListenerProvider parent();
	
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
	
	/**
	 * A builder for creating listener provider instances.
	 * 
	 * <p>Builder instances can be reused, it is safe to call {@link #build}
	 * multiple times to build multiple listener providers in series.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	public interface Builder
	{
		/**
		 * Adds listener implementation to the built listener provider. 
		 * If the listener provider already contains given implementation,
		 * then this method has no effect.
		 * @param implementation the listener type to add, not {@code null}
		 * @return this {@code Builder} object
		 * @throws NullPointerException if {@code type} is {@code null}
		 */
		public Builder add(Class<? extends Listener> implementation);
		
	    /**
	     * Adds each listener implementation to the built listener provider,
	     * ignoring duplicate elements.
	     * @param implementations the listener types to add
	     * @return this {@code Builder} object
	     * @throws NullPointerException if {@code implementations}
	     *         is {@code null} or contains a {@code null} element
	     */
		public Builder addAll(Iterable<Class<? extends Listener>> implementations);
		
	    /**
	     * Sets the parent listener provider of the built listener provider.
	     * @param provider the parent provider, not {@code null}
	     * @return this {@code Builder} object
	     * @throws NullPointerException if the provider is {@code null}
	     * @throws IllegalStateException if the provider is already set
	     */
		public Builder parent(ListenerProvider provider);
	
		/**
		 * Returns a newly created listener provider.
		 */
		public ListenerProvider build(); 
	}
}
