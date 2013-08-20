package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Resource;

final class GenericResourceService extends AbstractResourceService
{
	GenericResourceService(final ResourceProvider provider, final ResourceManager manager)
	{
		super(provider, manager);
	}
	
	@Override
	protected final void doStart()
	{
		for (Class<? extends sk.stuba.fiit.perconik.core.Listener> type: this.provider.types())
		{
			this.registerResourcesForClass(type);
		}
		
		this.notifyStarted();
	}

	@Override
	protected final void doStop()
	{
		this.manager.unregisterAll(sk.stuba.fiit.perconik.core.Listener.class);
	
		this.notifyStopped();
	}
	
	private final <L extends sk.stuba.fiit.perconik.core.Listener> void registerResourcesForClass(final Class<L> type)
	{
		Set<Resource<? super L>> resources = this.provider.forType(type);
		
		for (Resource<? super L> resource: resources)
		{
			this.manager.register(type, resource);
		}
	}
}
