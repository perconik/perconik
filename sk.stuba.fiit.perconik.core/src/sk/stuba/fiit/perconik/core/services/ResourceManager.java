package sk.stuba.fiit.perconik.core.services;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.collect.SetMultimap;

public interface ResourceManager
{
	public <L extends Listener> void register(Class<L> type, Resource<? super L> resource);

	public <L extends Listener> void unregister(Class<L> type, Resource<? super L> resource);
	
	public <L extends Listener> void unregisterAll(Class<L> type);

	public <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type);

	public <L extends Listener> Set<Resource<? super L>> registrable(Class<L> type);

	public SetMultimap<Class<? extends Listener>, Resource<?>> registrations();
}
