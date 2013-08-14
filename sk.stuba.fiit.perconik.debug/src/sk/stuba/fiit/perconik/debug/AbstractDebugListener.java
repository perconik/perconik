package sk.stuba.fiit.perconik.debug;

import java.util.Date;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.Strings;

public abstract class AbstractDebugListener extends DebugObject implements DebugListener
{
	protected AbstractDebugListener()
	{
	}
	
	protected AbstractDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	protected final void action(final String name)
	{
		this.put("%s %s ... ", name, this);
	}
	
	protected final void inform(final boolean result)
	{
		this.print(result == true ? "done" : "failed");
	}

	protected final void handle(final String name, final Exception e)
	{
		this.inform(false);
		this.error(name + " error", e);
	}

	private static enum InternalHook
	{
		PRE_REGISTER("Pre register hook")
		{
			@Override
			final boolean execute(final AbstractDebugListener listener)
			{
				return listener.preRegisterInternal();
			}
		},
		
		POST_REGISTER("Post register hook")
		{
			@Override
			final boolean execute(final AbstractDebugListener listener)
			{
				return listener.postRegisterInternal();
			}
		},
		
		PRE_UNREGISTER("Pre unregister hook")
		{
			@Override
			final boolean execute(final AbstractDebugListener listener)
			{
				return listener.preUnregisterInternal();
			}
		},
		
		POST_UNREGISTER("Post unregister hook")
		{
			@Override
			final boolean execute(final AbstractDebugListener listener)
			{
				return listener.postUnregisterInternal();
			}
		};
		
		private final String name;
		
		private InternalHook(final String name)
		{
			assert !name.isEmpty();
			
			this.name = name;
		}
		
		final void on(final AbstractDebugListener listener)
		{
			listener.action(this.name);
			
			try
			{
				listener.inform(this.execute(listener));
			}
			catch (Exception e)
			{
				listener.handle(this.name, e);
			}
		}
		
		abstract boolean execute(final AbstractDebugListener listener);
	
		@Override
		public final String toString()
		{
			return this.name;
		}
	}

	public final void preRegister()
	{
		InternalHook.PRE_REGISTER.on(this);
	}

	public final void postRegister()
	{
		InternalHook.POST_REGISTER.on(this);
	}

	public final void preUnregister()
	{
		InternalHook.PRE_UNREGISTER.on(this);
	}

	public final void postUnregister()
	{
		InternalHook.POST_UNREGISTER.on(this);
	}

	@SuppressWarnings("static-method")
	protected boolean preRegisterInternal()
	{
		return true;
	}

	@SuppressWarnings("static-method")
	protected boolean postRegisterInternal()
	{
		return true;
	}

	@SuppressWarnings("static-method")
	protected boolean preUnregisterInternal()
	{
		return true;
	}

	@SuppressWarnings("static-method")
	protected boolean postUnregisterInternal()
	{
		return true;
	}
	
	@Override
	public String toString()
	{
		return Strings.toCanonicalString(this);
	}

	protected final void printHeader(final String title)
	{
		this.put(Debug.dumpHeader(title));
	}

	protected final void printBlock(final Object key, final Object value)
	{
		this.put(Debug.dumpBlock(key, value));
	}

	protected final void printLine(final Object key, final Object value)
	{
		this.put(Debug.dumpLine(key, value));
	}

	protected final void printTime()
	{
		this.put(Debug.dumpTime());
	}

	protected final void printTime(final Date date)
	{
		this.put(Debug.dumpTime(date));
	}
}
