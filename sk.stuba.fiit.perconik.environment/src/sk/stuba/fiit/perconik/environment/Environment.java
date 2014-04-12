package sk.stuba.fiit.perconik.environment;

import java.lang.management.ManagementFactory;
import org.osgi.framework.Version;
import com.google.common.base.StandardSystemProperty;

/**
 * Bridge between plug-in environment and the native platform.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Environment
{
	/**
	 * Presence marker for the {@code DEBUG} environment variable.
	 */
	public static final boolean debug = getVariableAsBoolean("DEBUG");

	private Environment()
	{
		throw new AssertionError();
	}
	
	/**
	 * Returns the current JVM process identifier.
	 * @throws RuntimeException if accessing the process identifier fails.
	 */
	public static final int pid()
	{
		String name = ManagementFactory.getRuntimeMXBean().getName();

		try
		{
			return Integer.parseInt(name.substring(0, name.indexOf("@")));
		}
		catch (RuntimeException e)
		{
			throw new RuntimeException("Unable to get PID from " + name, e);
		}
	}

	public static final Version getJavaVersion()
	{
		return JavaVerifier.parseJavaVersion(StandardSystemProperty.JAVA_VERSION.value());
	}
	
	/**
	 * Returns the value of the specified environment variable.
	 * An environment variable is a system-dependent external named value. 
	 * @param name the name of the environment variable, not {@code null}
	 * @return the string value of the variable or {@code null}
	 *         if the variables is not defined in the system environment
	 *         or if {@code SecurityException} is thrown
	 */
	public static final String getVariable(final String name)
	{
		try
		{
			return System.getenv(name);
		}
		catch (SecurityException e)
		{
			return null;
		}
	}

	public static final boolean getVariableAsBoolean(final String name)
	{
		String value = getVariable(name);
		
		return value != null && Boolean.parseBoolean(value);
	}
}
