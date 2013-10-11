package sk.stuba.fiit.perconik.utilities.reflect;

public interface ClassResolver
{
	public Class<?> forName(final String name) throws ClassNotFoundException;
}
