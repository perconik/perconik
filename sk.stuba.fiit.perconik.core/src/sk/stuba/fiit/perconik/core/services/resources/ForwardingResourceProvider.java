package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ForwardingProvider;

/**
 * A resource provider which forwards all its method calls to another resource
 * provider. Subclasses should override one or more methods to modify the
 * behavior of the backing resource provider as desired per the decorator
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
public abstract class ForwardingResourceProvider extends ForwardingProvider implements ResourceProvider
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingResourceProvider()
	{
	}

	@Override
	protected abstract ResourceProvider delegate();

	public Resource<?> forName(String name)
	{
		return this.delegate().forName(name);
	}

	public <L extends Listener> Set<Resource<L>> forType(Class<L> type)
	{
		return this.delegate().forType(type);
	}

	public Set<String> names()
	{
		return this.delegate().names();
	}

	public Set<Class<? extends Listener>> types()
	{
		return this.delegate().types();
	}

	@Override
	public ResourceProvider parent()
	{
		return this.delegate().parent();
	}
}
