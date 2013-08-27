package sk.stuba.fiit.perconik.core.services.resources;

public final class ResourceInitializers
{
	private ResourceInitializers()
	{
		throw new AssertionError();
	}
	
	public static final ResourceInitializer create()
	{
		return new StandardResourceInitializer();
	}
}
