package sk.stuba.fiit.perconik.debug.services;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ResourceManager;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class DebugResourceManagerProxy extends DebugObjectProxy<ResourceManager> implements DebugResourceManager
{
	public DebugResourceManagerProxy(final ResourceManager manager)
	{
		super(manager);
	}
	
	public DebugResourceManagerProxy(final ResourceManager manager, final PluginConsole console)
	{
		super(manager, console);
	}

	public final <L extends Listener> void register(final Class<L> type, final Resource<L> resource)
	{
		this.print("Registering resource ", DebugResources.toString(resource), " to listener type ", type);
		
		this.delegate().register(type, resource);
		
		this.print("Resource ", DebugResources.toString(resource), " registered to listener type ", type);
	}

	public final <L extends Listener> void unregister(final Class<L> type, final Resource<L> resource)
	{
		this.print("Unregistering resource ", DebugResources.toString(resource), " from listener type ", type);
		
		this.delegate().unregister(type, resource);
		
		this.print("Resource ", DebugResources.toString(resource), " unregistered from listener type ", type);
	}

	public final Set<Resource<?>> registered()
	{
		return this.delegate().registered();
	}

	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		return this.delegate().assignable(type);
	}

	public final <L extends Listener> Set<Resource<? super L>> registerable(final Class<L> type)
	{
		return this.delegate().registerable(type);
	}
}
