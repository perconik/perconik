package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.ResourceNotRegistredException;
import sk.stuba.fiit.perconik.core.UnsupportedResourceException;
import sk.stuba.fiit.perconik.core.services.AbstractManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;

public abstract class AbstractListenerManager extends AbstractManager implements ListenerManager
{
	protected AbstractListenerManager()
	{
	}

	protected abstract ResourceManager manager();

	private final <L extends Listener> Set<Resource<? super L>> registrables(final L listener)
	{
		Set<Resource<? super L>> resources = this.manager().registrable((Class<L>) listener.getClass());

		if (resources.isEmpty())
		{
			throw new ResourceNotRegistredException("No registred resources for listener type " + listener.getClass().getName());
		}

		return resources;
	}
	
	public final <L extends Listener> void register(final L listener)
	{
		for (Resource<? super L> resource: this.registrables(listener))
		{
			// TODO consider
			try
			{
				resource.register(listener);
			}
			catch (UnsupportedResourceException e)
			{
				failure(e, "Unsupported resource %s failed registering listener %s", resource, listener);
			}
		}
	}

	public final <L extends Listener> void unregister(final L listener)
	{
		for (Resource<? super L> resource: this.registrables(listener))
		{
			try
			{
				resource.unregister(listener);
			}
			catch (UnsupportedResourceException e)
			{
				failure(e, "Unsupported resource %s failed unregistering listener %s", resource, listener);
			}
		}
	}
}
