package sk.stuba.fiit.perconik.debug.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.DebugResource;
import sk.stuba.fiit.perconik.debug.DebugResources;
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
		this.print(DebugResources.toString(this.delegate()), " is registering listener ", DebugListeners.toString(listener));
		
		this.delegate().register(listener);
	}

	public final void unregister(final L listener)
	{
		this.print(DebugResources.toString(this.delegate()), " is unregistering listener ", DebugListeners.toString(listener));
		
		this.delegate().unregister(listener);
	}

	public final void unregisterAll(final Class<? extends Listener> type)
	{
		this.print(DebugResources.toString(this.delegate()), " is unregistering all listeners");
		
		this.delegate().unregisterAll(type);
	}

	public final <U extends Listener> Collection<U> registered(Class<U> type)
	{
		return this.delegate().registered(type);
	}
}
