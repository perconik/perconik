package sk.stuba.fiit.perconik.environment;

/**
 * Bridge between <i>PerConIK</i> project and environment of a platform
 * on which it is running.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Environment
{
	/**
	 * Presence marker for the {@code DEBUG} environment variable.
	 */
	public static final boolean debug = System.getenv("DEBUG") != null;

	private Environment()
	{
		throw new AssertionError();
	}
}
