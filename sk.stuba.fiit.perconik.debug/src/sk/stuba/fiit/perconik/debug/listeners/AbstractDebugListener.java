package sk.stuba.fiit.perconik.debug.listeners;

import sk.stuba.fiit.perconik.core.listeners.Listener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.debug.Debug;
import com.google.common.base.Preconditions;

public abstract class AbstractDebugListener implements Listener
{
	private final PluginConsole console; 
	
	protected AbstractDebugListener()
	{
		this(Debug.getDefaultConsole());
	}
	
	protected AbstractDebugListener(final PluginConsole console)
	{
		this.console = Preconditions.checkNotNull(console);
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
		return this.getClass().getCanonicalName();
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
}
