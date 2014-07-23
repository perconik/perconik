package sk.stuba.fiit.perconik.core.debug.runtime;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import org.eclipse.core.runtime.Plugin;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.environment.Environment;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class DebugConsole implements PluginConsole
{
	private static final int MAX_BUFFER_SIZE = 8192;
	
	private final boolean enabled;
	
	private final PluginConsole console;
	
	private final SmartStringBuilder builder;
	
	private DebugConsole(final PluginConsole console)
	{
		this.enabled = Environment.debug;
		this.console = Preconditions.checkNotNull(console);
		this.builder = new SmartStringBuilder();
	}

	private static enum Factory implements DebugConsoleFactory
	{
		INSTANCE;

		public final DebugConsole create(final Plugin plugin)
		{
			return of(plugin);
		}
	}
	
	public static final DebugConsoleFactory factory()
	{
		return Factory.INSTANCE;
	}
	
	public static final DebugConsole of(final Plugin plugin)
	{
		return of(PluginConsoles.create(plugin));
	}

	public static final DebugConsole of(final PluginConsole console)
	{
		if (console instanceof DebugConsole)
		{
			return (DebugConsole) console;
		}
		
		return new DebugConsole(console);
	}
	
	private final boolean isEnabled()
	{
		return this.enabled;
	}
	
	public final PluginConsole append(@Nullable CharSequence s)
	{
		this.builder.append(s);
		
		return this;
	}

	public final PluginConsole append(@Nullable CharSequence s, int from, int to)
	{
		this.builder.append(s, from, to);
		
		return this;
	}

	public final PluginConsole append(char c)
	{
		this.builder.append(c);
		
		return this;
	}

	public final void close()
	{
	}

	public final void flush()
	{
	}
	
	public final void tab()
	{
		this.builder.tab();
	}

	public final void untab()
	{
		this.builder.untab();
	}

	private final void trim()
	{
		if (this.builder.capacity() >= MAX_BUFFER_SIZE)
		{
			this.builder.trimToSize();
		}
	}

	private final String indent(@Nullable final String message)
	{
		return this.builder.lines(message).toString();
	}
	
	private final String indent(final String format, final Object ... args)
	{
		return this.indent(String.format(format, args));
	}
	
	@Override
	public final void put(@Nullable final String message)
	{
		if (this.isEnabled())
		{
			this.console.put(indent(message));
			this.putHook();
		}
	}
	
	@Override
	public final void put(final String format, final Object ... args)
	{
		if (this.isEnabled())
		{
			this.console.put(indent(format, args));
			this.putHook();
		}
	}
	
	private final void putHook()
	{
		this.builder.setLength(0);
		this.trim();
	}

	@Override
	public final void print(@Nullable final String message)
	{
		if (this.isEnabled())
		{
			this.console.print(indent(message));
			this.printHook();
		}
	}

	@Override
	public final void print(final String format, final Object ... args)
	{
		if (this.isEnabled())
		{
			this.console.print(indent(format, args));
			this.printHook();
		}
	}
	
	private final void printHook()
	{
		this.builder.truncate();
		this.trim();
	}

	@Override
	public final void notice(final String message)
	{
		if (this.isEnabled())
		{
			this.console.notice(message);
		}
	}

	@Override
	public final void notice(final String format, final Object ... args)
	{
		if (this.isEnabled())
		{
			this.console.notice(format, args);
		}
	}

	@Override
	public final void warning(final String message)
	{
		if (this.isEnabled())
		{
			this.console.warning(message);
		}
	}

	@Override
	public final void warning(final String format, final Object ... args)
	{
		if (this.isEnabled())
		{
			this.console.warning(format, args);
		}
	}

	public final void error(String message)
	{
		if (this.isEnabled())
		{
			this.console.error(message);
		}
	}

	public final void error(String format, Object ... args)
	{
		if (this.isEnabled())
		{
			this.console.error(format, args);
		}
	}

	public final void error(Throwable failure, String message)
	{
		if (this.isEnabled())
		{
			this.console.error(failure, message);
		}
	}

	public final void error(Throwable failure, String format, Object ... args)
	{
		if (this.isEnabled())
		{
			this.console.error(failure, format, args);
		}
	}
}
