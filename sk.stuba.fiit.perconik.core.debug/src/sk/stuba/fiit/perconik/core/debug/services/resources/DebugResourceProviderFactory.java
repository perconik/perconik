package sk.stuba.fiit.perconik.core.debug.services.resources;

import javax.annotation.Nonnull;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProviderFactory;

public final class DebugResourceProviderFactory implements ResourceProviderFactory
{
	public DebugResourceProviderFactory()
	{
	}

	public final ResourceProvider create(@Nonnull final ResourceProvider parent)
	{
		return DebugResourceProviders.create(parent);
	}
}
