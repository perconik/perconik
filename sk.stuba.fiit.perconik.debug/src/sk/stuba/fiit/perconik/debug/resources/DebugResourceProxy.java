package sk.stuba.fiit.perconik.debug.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.DebugResource;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class DebugResourceProxy<L extends Listener> extends DebugObjectProxy<Resource<L>> implements DebugResource<L>
{
	public DebugResourceProxy(final Resource<L> resource)
	{
		super(resource);
	}

	public DebugResourceProxy(final Resource<L> resource, final PluginConsole console)
	{
		super(resource, console);
	}
	
	public final void register(final L listener)
	{
		this.print("Registering listener ", listener);
		
		this.delegate().register(listener);
		
		this.print("Listener ", listener, " registered");
	}

	public final void unregister(final L listener)
	{
		this.print("Registering listener ", listener);
		
		this.delegate().unregister(listener);
		
		this.print("Listener ", listener, " unregistered");
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
		this.print("Unregistering all listeners");
		
		this.delegate().unregisterAll(type);
	}

	public final <U extends Listener> Collection<U> registered(Class<U> type)
	{
		return this.delegate().registered(type);
	}
}
