package sk.stuba.fiit.perconik.utilities.reflect.resolver;

enum DefaultClassResolver implements ClassResolver
{
	INSTANCE;

	public final Class<?> forName(String name) throws ClassNotFoundException
	{
		return Class.forName(name);
	}
	
	@Override
	public final String toString()
	{
		return "DefaultClassResolver";
	}
}
