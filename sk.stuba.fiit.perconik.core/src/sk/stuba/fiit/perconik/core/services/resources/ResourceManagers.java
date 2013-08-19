package sk.stuba.fiit.perconik.core.services.resources;

public class ResourceManagers
{
	private ResourceManagers()
	{
		throw new AssertionError();
	}
	
	public static final ResourceManager create()
	{
		return new GenericResourceManager();
	}
}
