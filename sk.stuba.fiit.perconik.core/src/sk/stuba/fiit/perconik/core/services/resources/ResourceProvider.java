package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.IllegalResourceClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceInstantiationException;
import sk.stuba.fiit.perconik.core.ResourceNotFoundException;
import sk.stuba.fiit.perconik.core.services.Provider;

/**
 * An object responsible for providing {@link Resource} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceProvider extends Provider
{
	/**
	 * Returns a resource with the specified name.
	 * 
	 * <p>This method is guaranteed to return the same resource instance
	 * for the specified resource name every time it is invoked.
	 * 
	 * @param name the resource name, not an empty string or {@code null}
	 * @return the resulting {@code Resource} object
	 * 
	 * @throws IllegalArgumentException if the specified resource name
	 *         is an empty string
	 * @throws NullPointerException if the specified resource name
	 *         is {@code null}
	 * @throws ResourceNotFoundException if no definition of the resource
	 *         could be found by the specified name
	 * @throws IllegalResourceClassException if the resource implementation
	 *         class is invalid
	 * @throws ResourceInstantiationException if the resource instantiation
	 *         failed
	 */
	public Resource<?> forName(String name);
	
	/**
	 * Returns a set of resources supporting the specified listener type.
	 * 
	 * @param type the listener type, not {@code null}
	 * @return a set of resources supporting the specified listener type
	 * 
	 * @throws NullPointerException if the specified listener type
	 *         is {@code null}
	 * @throws ResourceNotFoundException if no definition of the resource
	 *         could be found by the specified name
	 * @throws IllegalResourceClassException if the resource implementation
	 *         class is invalid
	 * @throws ResourceInstantiationException if the resource instantiation
	 *         failed
	 */
	public <L extends Listener> Set<Resource<L>> forType(Class<L> type);
	
	/**
	 * Returns names of all provided resources.
	 */
	public Set<String> names();
	
	/**
	 * Returns listener types supported by provided resources.
	 */
	public Set<Class<? extends Listener>> types();
	
	/**
	 * Returns the resource provider's parent or {@code null} if there is no
	 * parent. Standard implementations should return system resource provider
	 * if there is no direct parent (only the system resource provider returns
	 * {@code null} as it is at the top of the resource provider hierarchy).
	 * @see ResourceProviders#getSystemProvider()
	 */
	@Override
	public ResourceProvider parent();
	
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
	 * A builder for creating resource provider instances.
	 * 
	 * <p>Builder instances can be reused, it is safe to call {@link #build}
	 * multiple times to build multiple resource providers in series.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	public interface Builder
	{
		/**
		 * Adds resource implementation to the built resource provider. 
		 * If the resource provider already contains given implementation,
		 * then this method has no effect.
		 * @param type the listener type with which the given resource
		 *             is to be associated, not {@code null}
		 * @param resource the resource to be associated with the given
		 *                 listener type, not {@code null}
		 * @return this {@code Builder} object
		 * @throws NullPointerException if {@code type} or {@code resource}
		 *         is {@code null}
		 */
		public <L extends Listener> Builder add(Class<L> type, Resource<? super L> resource);
		
	    /**
	     * Sets the parent resource provider of the built resource provider.
	     * @param provider the parent provider, not {@code null}
	     * @return this {@code Builder} object
	     * @throws NullPointerException if the provider is {@code null}
	     * @throws IllegalStateException if the provider is already set
	     */
		public Builder parent(ResourceProvider provider);
		
		/**
		 * Returns a newly created resource provider.
		 */
		public ResourceProvider build(); 
	}
}
