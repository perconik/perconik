package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.resources.ResourceService.Builder;

public class ResourceServices
{
	private ResourceServices()
	{
		throw new AssertionError();
	}
	
	public static final Builder builder()
	{
		return StandardResourceService.builder();
	}
}
