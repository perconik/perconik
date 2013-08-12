package sk.stuba.fiit.perconik.core.services;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

public interface ResourceService
{
	public <L extends Listener> void register(Class<L> type, Resource<L> resource);

	public <L extends Listener> void unregister(Class<L> type, Resource<L> resource);

	public Set<Resource<?>> registered();

	public <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type);

	public <L extends Listener> Set<Resource<? super L>> registerable(Class<L> type);
}
