package sk.stuba.fiit.perconik.debug.services.listeners;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.services.DebugNameableProxy;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;

public final class DebugListenerManagerProxy extends DebugNameableProxy implements DebugListenerManager
{
	private final ListenerManager manager;
	
	private DebugListenerManagerProxy(final ListenerManager manager, final DebugConsole console)
	{
		super(console);
		
		this.manager = Preconditions.checkNotNull(manager);
	}
	
	public static final DebugListenerManagerProxy wrap(final ListenerManager manager)
	{
		return wrap(manager, Debug.getDefaultConsole());
	}

	public static final DebugListenerManagerProxy wrap(final ListenerManager manager, final DebugConsole console)
	{
		if (manager instanceof DebugListenerManagerProxy)
		{
			return (DebugListenerManagerProxy) manager;
		}
		
		return new DebugListenerManagerProxy(manager, console);
	}
	
	public static final ListenerManager unwrap(final ListenerManager manager)
	{
		if (manager instanceof DebugListenerManagerProxy)
		{
			return ((DebugListenerManagerProxy) manager).delegate();
		}
		
		return manager;
	}
	
	@Override
	protected final ListenerManager delegate()
	{
		return this.manager;
	}

	public final <L extends Listener> void register(final L listener)
	{
		this.print("Registering listener %s", DebugListeners.toString(listener));
		this.tab();
		
		this.delegate().register(listener);
		
		this.untab();
	}

	public final <L extends Listener> void unregister(final L listener)
	{
		this.print("Unregistering listener %s", DebugListeners.toString(listener));
		this.tab();
		
		this.delegate().unregister(listener);
		
		this.untab();
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
		this.print("Unregistering all listeners assignable to listener type %s", DebugListeners.toString(type));
		this.tab();
		
		this.delegate().unregisterAll(type);
		
		this.untab();
	}

	public final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		return this.delegate().registered(type);
	}

	public final Multimap<Resource<?>, Listener> registrations()
	{
		return this.delegate().registrations();
	}
}
