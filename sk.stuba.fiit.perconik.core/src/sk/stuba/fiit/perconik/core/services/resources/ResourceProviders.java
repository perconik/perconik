package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider.Builder;

public class ResourceProviders
{
	private ResourceProviders()
	{
		throw new AssertionError();
	}
		
	public static final Builder builder()
	{
		return GenericResourceProvider.builder();
	}
}
