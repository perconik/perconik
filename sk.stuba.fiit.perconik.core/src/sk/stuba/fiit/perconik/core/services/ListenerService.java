package sk.stuba.fiit.perconik.core.services;

import java.util.Collection;
import java.util.Map;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

public interface ListenerService
{
	public <L extends Listener> void register(final L listener);

	public <L extends Listener> void unregister(final L listener);
	
	public void unregisterAll(final Class<? extends Listener> type);

	public <L extends Listener> Collection<L> registered(final Class<L> type);

	public Map<Resource<?>, Collection<Listener>> registrations();
}
