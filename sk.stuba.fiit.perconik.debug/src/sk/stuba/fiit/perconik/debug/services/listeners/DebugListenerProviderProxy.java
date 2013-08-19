package sk.stuba.fiit.perconik.debug.services.listeners;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugNameableProxy;

public class DebugListenerProviderProxy extends DebugNameableProxy<ListenerProvider> implements DebugListenerProvider
{
	private DebugListenerProviderProxy(final ListenerProvider provider, final DebugConsole console)
	{
		super(provider, console);
	}

	public static final DebugListenerProviderProxy wrap(final ListenerProvider provider)
	{
		return wrap(provider, Debug.getDefaultConsole());
	}
	
	public static final DebugListenerProviderProxy wrap(final ListenerProvider provider, final DebugConsole console)
	{
		if (provider instanceof DebugListenerProviderProxy)
		{
			return (DebugListenerProviderProxy) provider;
		}
		
		return new DebugListenerProviderProxy(provider, console);
	}

	public static final ListenerProvider unwrap(final ListenerProvider provider)
	{
		if (provider instanceof DebugListenerProviderProxy)
		{
			return ((DebugListenerProviderProxy) provider).delegate();
		}
		
		return provider;
	}
	
	public final <L extends Listener> L forClass(final Class<L> type)
	{
		this.put("Requesting listener for %s ... ", DebugListeners.toString(type));
		
		L listener = this.delegate().forClass(type);
		
		this.print(listener != null ? "done (" + DebugListeners.toString(listener) + ")" : "failed");
		
		return listener;
	}

	public final Class<? extends Listener> loadClass(final String name) throws ClassNotFoundException
	{
		return this.delegate().loadClass(name);
	}

	public final Iterable<Class<? extends Listener>> classes()
	{
		return this.delegate().classes();
	}
}
