package sk.stuba.fiit.perconik.debug;

import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.debug.plugin.Activator;

public final class Debug
{
	private static final PluginConsole console = PluginConsole.of(Activator.getDefault());
	
	private Debug()
	{
		throw new AssertionError();
	}
	
	public static final PluginConsole getDefaultConsole()
	{
		return console;
	}

	public static final void put(final String message)
	{
		console.put(message);
	}
	
	public static final void put(final String format, final Object ... args)
	{
		console.put(format, args);
	}
	
	public static final void print(final String message)
	{
		console.print(message);
	}
	
	public static final void print(final String format, final Object ... args)
	{
		console.print(format, args);
	}

	public static final void notice(final String message)
	{
		console.notice(message);
	}
	
	public static final void notice(final String format, Object ... args)
	{
		console.notice(format, args);
	}
	
	public static final void warning(final String message)
	{
		console.warning(message);
	}

	public static final void warning(final String format, Object ... args)
	{
		console.warning(format, args);
	}

	public static final void error(final String message, final Exception e)
	{
		console.error(message, e);
	}
}
