package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.Manager;
import com.google.common.collect.SetMultimap;

public interface ListenerManager extends Manager
{
	public <L extends Listener> void register(final L listener);

	public <L extends Listener> void unregister(final L listener);
	
	public void unregisterAll(final Class<? extends Listener> type);

	public <L extends Listener> Collection<L> registered(final Class<L> type);

	public SetMultimap<Resource<?>, Listener> registrations();
}
