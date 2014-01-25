package sk.stuba.fiit.perconik.eclipse.core.runtime;

import javax.annotation.Nullable;
import com.google.common.collect.ForwardingObject;

/**
 * A plug-in console which forwards all its method calls to another plug-in
 * console. Subclasses should override one or more methods to modify the
 * behavior of the backing plugin console as desired per the decorator
 * pattern.
 * 
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object.
 * See {@link ForwardingObject} for more details.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingPluginConsole extends ForwardingObject implements PluginConsole
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingPluginConsole()
	{
	}
	
	@Override
	protected abstract PluginConsole delegate();
	
	public PluginConsole append(@Nullable CharSequence s)
	{
		return this.delegate().append(s);
	}

	public PluginConsole append(@Nullable CharSequence s, int from, int to)
	{
		return this.delegate().append(s, from, to);
	}

	public PluginConsole append(char c)
	{
		return this.delegate().append(c);
	}

	public void close()
	{
		this.delegate().close();
	}

	public void flush()
	{
		this.delegate().flush();
	}

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

	public void error(String message)
	{
		this.delegate().error(message);
	}

	public void error(String format, Object ... args)
	{
		this.delegate().error(format, args);
	}

	public void error(Throwable failure, String message)
	{
		this.delegate().error(failure, message);
	}

	public void error(Throwable failure, String format, Object ... args)
	{
		this.delegate().error(failure, format, args);
	}
	
	
}
