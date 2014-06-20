package sk.stuba.fiit.perconik.core.debug.services.resources;

import sk.stuba.fiit.perconik.core.debug.DebugListener;
import sk.stuba.fiit.perconik.core.debug.resources.DebugListenerPool;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProviders;

public final class DebugResourceProviders
{
	private DebugResourceProviders()
	{
		throw new AssertionError();
	}

	public static final DebugResourceProvider create()
	{
		return create(ResourceProviders.getSystemProvider());
	}

	public static final DebugResourceProvider create(final ResourceProvider parent)
	{
		ResourceProvider.Builder builder = ResourceProviders.builder().parent(parent);
		
		builder.add(DebugListener.class, DebugListenerPool.getInstance());
		
		ResourceProvider provider = ResourceProviders.builder().parent(builder.build()).build();
		
		return DebugResourceProviderProxy.wrap(provider);
	}
}
