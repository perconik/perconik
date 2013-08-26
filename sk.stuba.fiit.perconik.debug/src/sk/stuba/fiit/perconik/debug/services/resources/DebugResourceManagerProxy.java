package sk.stuba.fiit.perconik.debug.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugNameableProxy;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;
import com.google.common.base.Preconditions;
import com.google.common.collect.SetMultimap;

public final class DebugResourceManagerProxy extends DebugNameableProxy implements DebugResourceManager
{
	private final ResourceManager manager;
	
	private DebugResourceManagerProxy(final ResourceManager manager, final DebugConsole console)
	{
		super(console);
		
		this.manager = Preconditions.checkNotNull(manager);
	}

	public static final DebugResourceManagerProxy wrap(final ResourceManager manager)
	{
		return wrap(manager, Debug.getDefaultConsole());
	}

	public static final DebugResourceManagerProxy wrap(final ResourceManager manager, final DebugConsole console)
	{
		if (manager instanceof DebugResourceManagerProxy)
		{
			return (DebugResourceManagerProxy) manager;
		}
		
		return new DebugResourceManagerProxy(manager, console);
	}

	public static final ResourceManager unwrap(final ResourceManager manager)
	{
		if (manager instanceof DebugResourceManagerProxy)
		{
			return ((DebugResourceManagerProxy) manager).delegate();
		}
		
		return manager;
	}
	
	@Override
	protected final ResourceManager delegate()
	{
		return this.manager;
	}

	public final <L extends Listener> void register(final Class<L> type, final Resource<? super L> resource)
	{
		this.print("Registering resource %s to listener type %s", DebugResources.toString(resource), DebugListeners.toString(type));
		this.tab();
		
		this.delegate().register(type, resource);
		
		this.untab();
	}

	public final <L extends Listener> void unregister(final Class<L> type, final Resource<? super L> resource)
	{
		this.print("Unregistering resource %s from listener type %s", DebugResources.toString(resource), DebugListeners.toString(type));
		this.tab();
		
		this.delegate().unregister(type, resource);
		
		this.untab();
	}

	public final <L extends Listener> void unregisterAll(final Class<L> type)
	{
		this.print("Unregistering all resources assignable to listener type %s", DebugListeners.toString(type));
		this.tab();
		
		this.delegate().unregisterAll(type);
		
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
