package sk.stuba.fiit.perconik.debug;

import javax.annotation.Nullable;
import org.eclipse.core.runtime.Plugin;
import com.google.common.base.Preconditions;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ForwardingPluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

public final class DebugConsole extends ForwardingPluginConsole
{
	private static final int MAX_BUFFER_SIZE = 8192;
	
	private final PluginConsole console;
	
	private final SmartStringBuilder builder;
	
	private DebugConsole(final PluginConsole console)
	{
		this.console = Preconditions.checkNotNull(console);
		this.builder = new SmartStringBuilder();
	}

	public static final DebugConsole of(final Plugin plugin)
	{
		return of(PluginConsole.of(plugin));
	}

	public static final DebugConsole of(final PluginConsole console)
	{
		return new DebugConsole(console);
	}
	
	@Override
	protected final PluginConsole delegate()
	{
		return this.console;
	}
	
	public final void tab()
	{
		this.builder.tab();
	}

	public final void untab()
	{
		this.builder.untab();
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
		this.console.put(indent(message));
		this.putHook();
	}
	
	private final void trim()
	{
		if (this.builder.capacity() >= MAX_BUFFER_SIZE)
		{
			this.builder.trimToSize();
		}
	}

	@Override
	public final void put(final String format, final Object ... args)
	{
		this.console.put(indent(format, args));
		this.putHook();
	}
	
	private final void putHook()
	{
		this.builder.setLength(0);
		this.trim();
	}

	@Override
	public final void print(@Nullable final String message)
	{
		this.console.print(indent(message));
		this.printHook();
	}

	@Override
	public final void print(final String format, final Object ... args)
	{
		this.console.print(indent(format, args));
		this.printHook();
	}
	
	private final void printHook()
	{
		this.builder.truncate();
		this.trim();
	}
}
