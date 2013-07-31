package sk.stuba.fiit.perconik.environment;

public final class Environment
{
	public static final boolean debug = System.getenv("DEBUG") != null;

	private Environment()
	{
		throw new AssertionError();
	}
}
