package sk.stuba.fiit.perconik.core.environment;

public final class Environment
{
	public static final boolean debug = System.getenv("DEBUG") != null;

	private Environment()
	{
		throw new AssertionError();
	}
}
