package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.AbstractGenericService;

/**
 * An abstract implementation of {@link ResourceService}. This skeleton
 * implementation provides an abstract builder mechanism to construct
 * instances of extended classes. It holds resource provider and manager.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractResourceService extends AbstractGenericService<ResourceProvider, ResourceManager> implements ResourceService
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractResourceService(final AbstractBuilder<?> builder)
	{
		super(builder);
	}

	/**
	 * An abstract builder for creating resource service instances.
	 * 
	 * <p>Builder instances can be reused, it is safe to call {@link #build}
	 * multiple times to build multiple listener services in series.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	protected static abstract class AbstractBuilder<B extends AbstractBuilder<B>> extends AbstractGenericBuilder<B, ResourceProvider, ResourceManager> implements Builder
	{
		/**
		 * Constructor for use by subclasses.
		 */
		protected AbstractBuilder()
		{
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public abstract ResourceService build();
	}
	
	/**
	 * {@inheritDoc}
	 * @throws IllegalStateException {@inheritDoc}
	 */
	public final ResourceProvider getResourceProvider()
	{
		this.checkRunning();
		
		return this.provider;
	}

	/**
	 * {@inheritDoc}
	 * @throws IllegalStateException {@inheritDoc}
	 */
	public final ResourceManager getResourceManager()
	{
		this.checkRunning();
		
		return this.manager;
	}
}
