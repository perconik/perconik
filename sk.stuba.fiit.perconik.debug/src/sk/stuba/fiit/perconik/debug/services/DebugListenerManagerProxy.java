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
		this.delegate().register(listener);
	}

	public final <L extends Listener> void unregister(final L listener)
	{
		this.delegate().unregister(listener);
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
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
