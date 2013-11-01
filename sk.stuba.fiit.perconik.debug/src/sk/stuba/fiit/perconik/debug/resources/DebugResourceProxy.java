package sk.stuba.fiit.perconik.debug.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugRegistrableProxy;
import sk.stuba.fiit.perconik.debug.DebugResource;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.debug.annotations.DebugProxy;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;
import com.google.common.base.Preconditions;

@DebugProxy
public final class DebugResourceProxy<L extends Listener> extends DebugRegistrableProxy implements DebugResource<L>
{
	private final Resource<L> resource;
	
	private DebugResourceProxy(final Resource<L> resource, final DebugConsole console)
	{
		super(console);
		
		this.resource = Preconditions.checkNotNull(resource);
	}
	
	public static final <L extends Listener> DebugResourceProxy<L> wrap(final Resource<L> resource)
	{
		return wrap(resource, Debug.getDefaultConsole());
	}

	public static final <L extends Listener> DebugResourceProxy<L> wrap(final Resource<L> resource, final DebugConsole console)
	{
		if (resource instanceof DebugResourceProxy)
		{
			return (DebugResourceProxy<L>) resource;
		}
		
		return new DebugResourceProxy<>(resource, console);
	}
	
	public static final <L extends Listener> Resource<L> unwrap(final Resource<L> resource)
	{
		if (resource instanceof DebugResourceProxy)
		{
			return ((DebugResourceProxy<L>) resource).delegate();
		}
		
		return resource;
	}
	
	@Override
	protected final Resource<L> delegate()
	{
		return this.resource;
	}

	public final void register(final L listener)
	{
		this.print("Registering listener %s to resource %s", DebugListeners.toString(listener), DebugResources.toString(this.delegate()));
		this.tab();		
		
		this.delegate().register(listener);
		
		this.untab();
	}

	public final void unregister(final L listener)
	{
		this.print("Unregistering listener %s from resource %s", DebugListeners.toString(listener), DebugResources.toString(this.delegate()));
		this.tab();
		
		this.delegate().unregister(listener);
		
		this.untab();
	}

	// TODO rm
//	public final void unregisterAll(final Class<? extends Listener> type)
//	{
//		this.print("Unregistering all listeners from resource %s", DebugResources.toString(this.delegate()));
//		this.tab();
//		
//		this.delegate().unregisterAll(type);
//		
//		this.untab();
//	}

	public final <U extends Listener> Collection<U> registered(final Class<U> type)
	{
		return this.delegate().registered(type);
	}

	public final boolean registered(Listener listener)
	{
		return this.delegate().registered(listener);
	}

	public final String getName()
	{
		return this.delegate().getName();
	}
}
