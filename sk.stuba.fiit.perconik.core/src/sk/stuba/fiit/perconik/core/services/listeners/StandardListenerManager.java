package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;
import java.util.List;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

final class StandardListenerManager extends AbstractListenerManager
{
	StandardListenerManager()
	{
	}

	@Override
	protected final ResourceManager manager()
	{
		return Services.getResourceService().getResourceManager();
	}
	
	public final void unregisterAll(final Class<? extends Listener> type)
	{
		for (Resource<?> resource: this.manager().assignables(type))
		{
			resource.unregisterAll(type);
		}
	}
	
	public final SetMultimap<Resource<?>, Listener> registrations()
	{
		SetMultimap<Resource<?>, Listener> registrations = HashMultimap.create();
		
		for (Resource<?> resource: this.manager().assignables(Listener.class))
		{
			registrations.putAll(resource, resource.registered(Listener.class));
		}
		
		return registrations;
	}

	public final <L extends Listener> Collection<L> registered(final Class<L> type)
	{
		List<L> listeners = Lists.newArrayList();
		
		for (Resource<? extends L> resource: this.manager().assignables(type))
		{
			listeners.addAll(resource.registered(type));
		}
		
		return listeners;
	}

	public boolean registered(Listener listener)
	{
		return this.manager().assignables(listener.getClass()).contains(listener);
	}
}
