package sk.stuba.fiit.perconik.debug.services;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.ListenerProvider;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class DebugListenerProviderProxy extends DebugObjectProxy<ListenerProvider> implements DebugListenerProvider
{
	public DebugListenerProviderProxy(final ListenerProvider provider)
	{
		super(provider);
	}
	
	public DebugListenerProviderProxy(final ListenerProvider provider, final PluginConsole console)
	{
		super(provider, console);
	}

	public final <L extends Listener> L forClass(final Class<L> type)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public final Class<? extends Listener> loadClass(final String name) throws ClassNotFoundException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public final Iterable<Class<? extends Listener>> loadedClasses()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
