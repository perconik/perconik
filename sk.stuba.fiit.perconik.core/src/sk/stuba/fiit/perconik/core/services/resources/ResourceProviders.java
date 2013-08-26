package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider.Builder;

public class ResourceProviders
{
	private ResourceProviders()
	{
		throw new AssertionError();
	}
	
	public static final ResourceProvider getSystemProvider()
	{
		return SystemResourceProvider.getInstance();
	}
	
	public static final Builder builder()
	{
		return StandardResourceProvider.builder();
	}
}
