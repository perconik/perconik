package sk.stuba.fiit.perconik.core.services.resources;

public class ResourceServices
{
	private ResourceServices()
	{
		throw new AssertionError();
	}
	
	public static final ResourceService create(final ResourceProvider provider, final ResourceManager manager)
	{
		return new StandardResourceService(provider, manager);
	}
}
