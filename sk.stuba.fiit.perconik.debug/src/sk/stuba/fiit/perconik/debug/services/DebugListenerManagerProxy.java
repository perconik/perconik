package sk.stuba.fiit.perconik.debug.services;

import java.util.Collection;
import java.util.Map;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ListenerManager;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class DebugListenerManagerProxy extends DebugObjectProxy<ListenerManager> implements DebugListenerManager
{
	public DebugListenerManagerProxy(final ListenerManager manager)
	{
		super(manager);
	}
	
	public DebugListenerManagerProxy(final ListenerManager manager, final PluginConsole console)
	{
		super(manager, console);
	}

	public final <L extends Listener> void register(final L listener)
	{
		// TODO Auto-generated method stub
		
	}

	public final <L extends Listener> void unregister(final L listener)
	{
		// TODO Auto-generated method stub
		
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
		// TODO Auto-generated method stub
		
	}

	public final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public final Map<Resource<?>, Collection<Listener>> registrations()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
