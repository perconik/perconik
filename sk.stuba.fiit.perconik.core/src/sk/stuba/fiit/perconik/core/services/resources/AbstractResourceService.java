package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.AbstractGenericService;

public abstract class AbstractResourceService extends AbstractGenericService<ResourceProvider, ResourceManager, ResourceInitializer> implements ResourceService
{
	protected AbstractResourceService(final AbstractBuilder<?> builder)
	{
		super(builder);
	}
	
	protected static abstract class AbstractBuilder<B extends AbstractBuilder<B>> extends AbstractGenericBuilder<B, ResourceProvider, ResourceManager, ResourceInitializer> implements Builder
	{
		@Override
		public abstract ResourceService build();
	}
	
	public final ResourceProvider getResourceProvider()
	{
		this.checkRunning();
		
		return this.provider;
	}

	public final ResourceManager getResourceManager()
	{
		this.checkRunning();
		
		return this.manager;
	}

	public final ResourceInitializer getResourceInitializer()
	{
		this.checkRunning();
		
		return this.initializer;
	}
}
