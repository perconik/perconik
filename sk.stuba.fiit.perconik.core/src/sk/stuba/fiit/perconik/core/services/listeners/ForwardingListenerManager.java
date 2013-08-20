package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ForwardingManager;
import com.google.common.collect.Multimap;

public abstract class ForwardingListenerManager extends ForwardingManager implements ListenerManager
{
	protected ForwardingListenerManager()
	{
	}
	
	@Override
	protected abstract ListenerManager delegate();

	public <L extends Listener> void register(L listener)
	{
		this.delegate().register(listener);
	}

	public <L extends Listener> void unregister(L listener)
	{
		this.delegate().unregister(listener);
	}

	public void unregisterAll(Class<? extends Listener> type)
	{
		this.delegate().unregisterAll(type);
	}

	public <L extends Listener> Collection<L> registered(Class<L> type)
	{
		return this.delegate().registered(type);
	}

	public Multimap<Resource<?>, Listener> registrations()
	{
		return this.delegate().registrations();
	}
}
