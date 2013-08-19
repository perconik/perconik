package sk.stuba.fiit.perconik.eclipse.core.runtime;

import com.google.common.collect.ForwardingObject;

public abstract class ForwardingPluginConsole extends ForwardingObject
{
	protected ForwardingPluginConsole()
	{
	}
	
	@Override
	protected abstract PluginConsole delegate();
	
	public void put(final String message)
	{
		this.delegate().put(message);
	}

	public void put(final String format, final Object ... args)
	{
		this.delegate().put(format, args);
	}

	public void print(final String message)
	{
		this.delegate().print(message);
	}

	public void print(final String format, final Object ... args)
	{
		this.delegate().print(format, args);
	}
	
	public void notice(final String message)
	{
		this.delegate().notice(message);
	}

	public void notice(final String format, Object ... args)
	{
		this.delegate().notice(format, args);
	}

	public void warning(final String message)
	{
		this.delegate().warning(message);
	}

	public void warning(final String format, Object ... args)
	{
		this.delegate().warning(format, args);
	}

	public void error(final String message, final Throwable failure)
	{
		this.delegate().error(message, failure);
	}
}
