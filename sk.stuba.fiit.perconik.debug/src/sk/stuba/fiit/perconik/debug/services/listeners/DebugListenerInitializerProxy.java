package sk.stuba.fiit.perconik.debug.services.listeners;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerInitializer;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;
import com.google.common.base.Preconditions;

public final class DebugListenerInitializerProxy extends DebugObjectProxy implements DebugListenerInitializer
{
	private final ListenerInitializer initializer;
	
	private DebugListenerInitializerProxy(final ListenerInitializer initializer, final DebugConsole console)
	{
		super(console);
		
		this.initializer = Preconditions.checkNotNull(initializer);
	}
	
	public static final DebugListenerInitializerProxy wrap(final ListenerInitializer initializer)
	{
		return wrap(initializer, Debug.getDefaultConsole());
	}

	public static final DebugListenerInitializerProxy wrap(final ListenerInitializer initializer, final DebugConsole console)
	{
		if (initializer instanceof DebugListenerInitializerProxy)
		{
			return (DebugListenerInitializerProxy) initializer;
		}
		
		return new DebugListenerInitializerProxy(initializer, console);
	}
	
	public static final ListenerInitializer unwrap(final ListenerInitializer initializer)
	{
		if (initializer instanceof DebugListenerInitializerProxy)
		{
			return ((DebugListenerInitializerProxy) initializer).delegate();
		}
		
		return initializer;
	}

	@Override
	protected final ListenerInitializer delegate()
	{
		return this.initializer;
	}

	public final void run()
	{
		this.print("Registering listeners:");
		this.tab();
		
		this.delegate().run();

		this.untab();
	}
}
