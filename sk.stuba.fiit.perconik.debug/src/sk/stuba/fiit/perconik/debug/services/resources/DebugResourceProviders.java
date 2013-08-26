package sk.stuba.fiit.perconik.debug.services.resources;

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
		ResourceProvider provider = ResourceProviders.builder().parent(parent).build();
		
		return DebugResourceProviderProxy.wrap(provider);
	}
}
