package sk.stuba.fiit.perconik.debug;

import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;
import com.google.common.base.Preconditions;

public abstract class AbstractDebugObject implements DebugObject
{
	private final DebugConsole console;
	
	protected AbstractDebugObject()
	{
		this(Debug.getDefaultConsole());
	}
	
	protected AbstractDebugObject(final DebugConsole console)
	{
		this.console = Preconditions.checkNotNull(console);
	}
	
	protected final void tab()
	{
		this.console.tab();
	}
	
	protected final void untab()
	{
		this.console.untab();
	}
	
	protected final void put(final String message)
	{
		this.console.put(message);
	}

	protected final void put(final String format, final Object ... args)
	{
		this.console.put(format, args);
	}

	protected final void print(final String message)
	{
		this.console.print(message);
	}

	protected final void print(final String format, final Object ... args)
	{
		this.console.print(format, args);
	}
	
	protected final void notice(final String message)
	{
		this.console.notice(message);
	}

	protected final void notice(final String format, Object ... args)
	{
		this.console.notice(format, args);
	}

	protected final void warning(final String message)
	{
		this.console.warning(message);
	}

	protected final void warning(final String format, Object ... args)
	{
		this.console.warning(format, args);
	}

	protected final void error(final String message, final Exception e)
	{
		this.console.error(message, e);
	}

	public final DebugConsole getDebugConsole()
	{
		return this.console;
	}
}
