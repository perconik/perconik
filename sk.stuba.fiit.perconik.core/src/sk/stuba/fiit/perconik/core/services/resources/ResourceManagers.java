package sk.stuba.fiit.perconik.core.services.resources;

public final class ResourceManagers
{
	private ResourceManagers()
	{
		throw new AssertionError();
	}
	
	public static final ResourceManager create()
	{
		return new StandardResourceManager();
	}
}
