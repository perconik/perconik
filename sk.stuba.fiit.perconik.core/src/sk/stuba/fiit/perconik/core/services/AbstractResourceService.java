package sk.stuba.fiit.perconik.core.services;

public abstract class AbstractResourceService extends AbstractService<ResourceProvider, ResourceManager> implements ResourceService
{
	protected AbstractResourceService(final Builder builder)
	{
		super(builder);
	}
	
	protected static abstract class Builder extends AbstractService.Builder<ResourceProvider, ResourceManager>
	{
		protected Builder()
		{
		}

		@Override
		protected abstract AbstractResourceService build();
	}

	public final ResourceProvider getResourceProvider()
	{
		return this.provider;
	}

	public final ResourceManager getResourceManager()
	{
		return this.manager;
	}
}
