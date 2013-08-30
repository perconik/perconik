package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.ProviderFactory;

public interface ResourceProviderFactory extends ProviderFactory<ResourceProvider>
{
	@Override
	public ResourceProvider create(ResourceProvider parent);
}
