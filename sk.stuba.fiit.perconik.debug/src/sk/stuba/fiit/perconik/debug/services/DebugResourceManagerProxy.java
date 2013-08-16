package sk.stuba.fiit.perconik.debug.services;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ResourceManager;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;
import sk.stuba.fiit.perconik.debug.DebugResources;
import com.google.common.collect.SetMultimap;

public class DebugResourceManagerProxy extends DebugObjectProxy<ResourceManager> implements DebugResourceManager
{
	private DebugResourceManagerProxy(final ResourceManager manager, final DebugConsole console)
	{
		super(manager, console);
	}

	public static final DebugResourceManagerProxy of(final ResourceManager manager)
	{
		return of(manager, Debug.getDefaultConsole());
	}

	public static final DebugResourceManagerProxy of(final ResourceManager manager, final DebugConsole console)
	{
		if (manager instanceof DebugResourceManagerProxy)
		{
			return (DebugResourceManagerProxy) manager;
		}
		
		return new DebugResourceManagerProxy(manager, console);
	}

	public final <L extends Listener> void register(final Class<L> type, final Resource<L> resource)
	{
		this.print("Registering resource %s to listener type %s", DebugResources.toString(resource), DebugListeners.toString(type));
		this.tab();
		
		this.delegate().register(type, resource);
		
		this.untab();
	}

	public final <L extends Listener> void unregister(final Class<L> type, final Resource<L> resource)
	{
		this.print("Unregistering resource %s from listener type %s", DebugResources.toString(resource), DebugListeners.toString(type));
		this.tab();
		
		this.delegate().unregister(type, resource);
		
		this.untab();
	}

	public final <L extends Listener> Set<Resource<? extends L>> assignable(final Class<L> type)
	{
		return this.delegate().assignable(type);
	}

	public final <L extends Listener> Set<Resource<? super L>> registrable(final Class<L> type)
	{
		return this.delegate().registrable(type);
	}

	public final SetMultimap<Class<? extends Listener>, Resource<?>> registrations()
	{
		return this.delegate().registrations();
	}
}
