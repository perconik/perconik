package sk.stuba.fiit.perconik.core.services.listeners;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.services.Service;

/**
 * An object with an operational state providing access to the underlying
 * listener provider and manager. Listener service also has asynchronous
 * lifecycle methods to transition between states. Listener provider and
 * manager encapsulated by this service are only accessible while the
 * service is running.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerService extends Service
{
	/**
	 * Gets the listener provider.
	 * @throws IllegalStateException if the service is not running
	 */
	public ListenerProvider getListenerProvider();
	
	/**
	 * Gets the listener manager.
	 * @throws IllegalStateException if the service is not running
	 */
	public ListenerManager getListenerManager();
	
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
	 * A builder for creating listener service instances.
	 * 
	 * <p>Builder instances can be reused, it is safe to call {@link #build}
	 * multiple times to build multiple listener services in series.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	public static interface Builder
	{
	    /**
	     * Sets the listener provider of the built listener service.
	     * @param provider the provider, not {@code null}
		 * @return this {@code Builder} object
	     * @throws NullPointerException if the provider is {@code null}
	     * @throws IllegalStateException if the provider is already set
	     */
		public Builder provider(ListenerProvider provider);
		
	    /**
	     * Sets the listener manager of the built listener service.
	     * @param manager the provider, not {@code null}
	     * @return this {@code Builder} object
	     * @throws NullPointerException if the manager is {@code null}
	     * @throws IllegalStateException if the manager is already set
	     */
		public Builder manager(ListenerManager manager);
		
		/**
		 * Returns a newly created listener service.
		 */
		public ListenerService build(); 
	}
}
