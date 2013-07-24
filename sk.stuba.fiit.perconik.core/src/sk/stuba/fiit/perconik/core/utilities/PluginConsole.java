package sk.stuba.fiit.perconik.core.utilities;

import java.io.PrintStream;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import sk.stuba.fiit.perconik.core.environment.Environment;
import com.google.common.base.Preconditions;

public final class PluginConsole
{
	private final Plugin plugin;
	
	private final PrintStream out;
	
	private PluginConsole(final Plugin plugin, final PrintStream out)
	{
		this.plugin = Preconditions.checkNotNull(plugin);
		this.out    = Preconditions.checkNotNull(out);
	}
	
	public static final PluginConsole of(final Plugin plugin)
	{
		return new PluginConsole(plugin, System.out);
	}
	
	private final void log(final Status status)
	{
		this.plugin.getLog().log(status);
	}
	
	public final void print(final String message)
	{
		if (Environment.debug)
		{
			this.out.println(message);
		}
	}
	
	public final void print(final String format, final Object ... args)
	{
		print(String.format(format, args));
	}

	public final void notice(final String message)
	{
		if (Environment.debug)
		{
			log(new Status(IStatus.INFO, getPluginId(), message));
		}
	}
	
	public final void notice(final String format, Object ... args)
	{
		notice(String.format(format, args));
	}
	
	public final void warning(final String message)
	{
		if (Environment.debug)
		{
			log(new Status(IStatus.WARNING, getPluginId(), message));
		}
	}

	public final void warning(final String format, Object ... args)
	{
		warning(String.format(format, args));
	}

	public final void error(final String message, final Exception e)
	{
		log(new Status(IStatus.ERROR, getPluginId(), message, e));
	}

	private final String getPluginId()
	{
		return this.plugin.getBundle().getSymbolicName();
	}
}
