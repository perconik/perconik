package sk.stuba.fiit.perconik.core.services;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Resource;

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

	@Override
	protected final void doStart()
	{
		for (Class<? extends sk.stuba.fiit.perconik.core.Listener> type: this.provider.classes())
		{
			this.registerResourcesForClass(type);
		}
		
		this.notifyStarted();
	}

	@Override
	protected final void doStop()
	{
		//this.manager.unregisterAll(sk.stuba.fiit.perconik.core.Listener.class); // TODO check
	
		this.notifyStopped();
	}

	private final <L extends sk.stuba.fiit.perconik.core.Listener> void registerResourcesForClass(final Class<L> type)
	{
		Set<Resource<? super L>> resources = this.provider.forClass(type);
		
		for (Resource<? super L> resource: resources)
		{
			this.manager.register(type, resource);
		}
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
