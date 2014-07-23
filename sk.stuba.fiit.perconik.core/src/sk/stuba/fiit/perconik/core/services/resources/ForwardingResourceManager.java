package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ForwardingManager;

/**
 * A resource manager which forwards all its method calls to another resource
 * manager. Subclasses should override one or more methods to modify the
 * behavior of the backing resource manager as desired per the decorator
 * pattern.
 * 
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object. See
 * {@link com.google.common.collect.ForwardingObject ForwardingObject}
 * for more details.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingResourceManager extends ForwardingManager implements ResourceManager
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingResourceManager()
	{
	}

	@Override
	protected abstract ResourceManager delegate(); 

	public <L extends Listener> void register(Class<L> type, Resource<? super L> resource)
	{
		this.delegate().register(type, resource);
	}

	public <L extends Listener> void unregister(Class<L> type, Resource<? super L> resource)
	{
		this.delegate().unregister(type, resource);
	}

	public <L extends Listener> void unregisterAll(Class<L> type)
	{
		this.delegate().unregisterAll(type);
	}

	public <L extends Listener> Set<Resource<? extends L>> assignables(Class<L> type)
	{
		return this.delegate().assignables(type);
	}

	public <L extends Listener> Set<Resource<? super L>> registrables(Class<L> type)
	{
		return this.delegate().registrables(type);
	}

	public SetMultimap<Class<? extends Listener>, Resource<?>> registrations()
	{
		return this.delegate().registrations();
	}
}
