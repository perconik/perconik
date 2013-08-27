package sk.stuba.fiit.perconik.core.services;

import java.util.Set;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import sk.stuba.fiit.perconik.core.listeners.DefaultListeners;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;

public final class Services
{
	static
	{
		Internals.setApi(ResourceService.class, new Supplier<ResourceService>()
		{
			public final ResourceService get()
			{
				return DefaultResources.getDefaultResourceService();
			}
		});

		Internals.setApi(ListenerService.class, new Supplier<ListenerService>()
		{
			public final ListenerService get()
			{
				return DefaultListeners.getDefaultListenerService();
			}
		});
	}

	private Services()
	{
		throw new AssertionError();
	}

	static final Set<Service> inStartOrder()
	{
		ImmutableSet.Builder<Service> builder = ImmutableSet.builder();
		
		builder.add(Services.getResourceService());
		builder.add(Services.getListenerService());
	
		return builder.build();
	}
	
	static final Set<Service> inStopOrder()
	{
		return ImmutableSet.copyOf(Lists.reverse(Lists.newArrayList(inStartOrder())));
	}
	
	public static final void setResourceService(final ResourceService service)
	{
		Internals.setApi(ResourceService.class, service);
	}

	public static final void setListenerService(final ListenerService service)
	{
		Internals.setApi(ListenerService.class, service);
	}

	public static final ResourceService getResourceService()
	{
		return Internals.getApi(ResourceService.class);
	}

	public static final ListenerService getListenerService()
	{
		return Internals.getApi(ListenerService.class);
	}
}
