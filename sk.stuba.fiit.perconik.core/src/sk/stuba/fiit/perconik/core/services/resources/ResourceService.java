package sk.stuba.fiit.perconik.core.services.resources;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.services.Service;

/**
 * An object with an operational state providing access to the underlying
 * resource provider and manager. Resource service also has asynchronous
 * lifecycle methods to transition between states. Resource provider and
 * manager encapsulated by this service are only accessible while the
 * service is running.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceService extends Service
{
	/**
	 * Gets the resource provider.
	 * @throws IllegalStateException if the service is not running
	 */
	public ResourceProvider getResourceProvider();
	
	/**
	 * Gets the resource manager.
	 * @throws IllegalStateException if the service is not running
	 */
	public ResourceManager getResourceManager();
	
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
	 * A builder for creating resource service instances.
	 * 
	 * <p>Builder instances can be reused, it is safe to call {@link #build}
	 * multiple times to build multiple resource services in series.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	public static interface Builder
	{
	    /**
	     * Sets the resource provider of the built resource service.
	     * @param provider the provider, not {@code null}
	     * @return this {@code Builder} object
	     * @throws NullPointerException if the provider is {@code null}
	     * @throws IllegalStateException if the provider is already set
	     */
		public Builder provider(ResourceProvider provider);
		
	    /**
	     * Sets the resource manager of the built resource service.
	     * @param manager the provider, not {@code null}
	     * @return this {@code Builder} object
	     * @throws NullPointerException if the manager is {@code null}
	     * @throws IllegalStateException if the manager is already set
	     */
		public Builder manager(ResourceManager manager);
		
		/**
		 * Returns a newly created resource service.
		 */
		public ResourceService build(); 
	}
}
