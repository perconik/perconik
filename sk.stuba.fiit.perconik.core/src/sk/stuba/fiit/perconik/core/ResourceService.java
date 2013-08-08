package sk.stuba.fiit.perconik.core;

import java.util.Set;

public interface ResourceService
{
	public <L extends Listener> void register(Class<L> type, Resource<L> resource);

	public <L extends Listener> void unregister(Class<L> type, Resource<L> resource);

	public Set<Resource<?>> registered();

	public <L extends Listener> Set<Resource<? super L>> registerable(Class<L> type);

	public <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type);
}
