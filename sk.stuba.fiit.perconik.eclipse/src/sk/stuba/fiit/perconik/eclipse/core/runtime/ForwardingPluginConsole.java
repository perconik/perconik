package sk.stuba.fiit.perconik.eclipse.core.runtime;

import javax.annotation.Nullable;
import com.google.common.collect.ForwardingObject;

public abstract class ForwardingPluginConsole extends ForwardingObject implements PluginConsole
{
	protected ForwardingPluginConsole()
	{
	}
	
	@Override
	protected abstract PluginConsole delegate();
	
	public void put(@Nullable String message)
	{
		this.delegate().put(message);
	}

	public void put(String format, Object ... args)
	{
		this.delegate().put(format, args);
	}

	public void print(@Nullable String message)
	{
		this.delegate().print(message);
	}

	public void print(String format, Object ... args)
	{
		this.delegate().print(format, args);
	}
	
	public void notice(String message)
	{
		this.delegate().notice(message);
	}

	public void notice(String format, Object ... args)
	{
		this.delegate().notice(format, args);
	}

	public void warning(String message)
	{
		this.delegate().warning(message);
	}

	public void warning(String format, Object ... args)
	{
		this.delegate().warning(format, args);
	}

	public void error(String message, Throwable failure)
	{
		this.delegate().error(message, failure);
	}
}
