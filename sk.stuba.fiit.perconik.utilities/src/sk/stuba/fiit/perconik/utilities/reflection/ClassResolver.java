package sk.stuba.fiit.perconik.utilities.reflection;

public interface ClassResolver
{
	public Class<?> forName(final String name) throws ClassNotFoundException;
}
