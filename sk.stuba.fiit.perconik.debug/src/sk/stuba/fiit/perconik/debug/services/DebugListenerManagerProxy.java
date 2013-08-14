package sk.stuba.fiit.perconik.debug.services;

import java.util.Collection;
import java.util.Map;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ListenerManager;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class DebugListenerManagerProxy extends DebugObjectProxy<ListenerManager> implements DebugListenerManager
{
	private DebugListenerManagerProxy(final ListenerManager manager, final PluginConsole console)
	{
		super(manager, console);
	}
	
	public static final DebugListenerManagerProxy of(final ListenerManager manager)
	{
		return of(manager, Debug.getDefaultConsole());
	}

	public static final DebugListenerManagerProxy of(final ListenerManager manager, final PluginConsole console)
	{
		if (manager instanceof DebugListenerManagerProxy)
		{
			return (DebugListenerManagerProxy) manager;
		}
		
		return new DebugListenerManagerProxy(manager, console);
	}

	public final <L extends Listener> void register(final L listener)
	{
		this.print("Registering listener ", DebugListeners.toString(listener));
		
		this.delegate().register(listener);
		
		this.print("Listener ", DebugListeners.toString(listener), " registered");
	}

	public final <L extends Listener> void unregister(final L listener)
	{
		this.print("Unregistering listener ", DebugListeners.toString(listener));
		
		this.delegate().unregister(listener);
		
		this.print("Listener ", DebugListeners.toString(listener), " unregistered");
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
		this.print("Unregistering all listeners assignable to ", type);
		
		this.delegate().unregisterAll(type);
	}

	public final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		return this.delegate().registered(type);
	}

	public final Map<Resource<?>, Collection<Listener>> registrations()
	{
		return this.delegate().registrations();
	}
}
