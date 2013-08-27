package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.services.Services;

final class StandardResourceInitializer implements ResourceInitializer
{
	StandardResourceInitializer()
	{
	}
	
	public final void run()
	{
		ResourceProvider provider = Services.getResourceService().getResourceProvider();
		
		for (Class<? extends Listener> type: provider.types())
		{
			register(provider, type);
		}
	}
	
	private final static <L extends Listener> void register(final ResourceProvider provider, final Class<L> type)
	{
		Set<Resource<? super L>> resources = provider.forType(type);
		
		for (Resource<? super L> resource: resources)
		{
			Resources.register(type, resource);
		}
	}
}
