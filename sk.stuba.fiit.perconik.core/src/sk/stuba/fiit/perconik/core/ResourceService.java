package sk.stuba.fiit.perconik.core;

import java.util.Set;

public interface ResourceService
{
	public <T extends Listener> void register(Class<T> type, Resource<T> resource);

	public <T extends Listener> void unregister(Class<T> type, Resource<T> resource);

	public Set<Resource<?>> registered();

	public <T extends Listener> Set<Resource<? super T>> registerable(Class<T> type);

	public <T extends Listener> Set<Resource<? extends T>> assignable(Class<T> type);
}
