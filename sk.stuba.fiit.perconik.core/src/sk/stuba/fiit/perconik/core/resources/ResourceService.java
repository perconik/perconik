package sk.stuba.fiit.perconik.core.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.listeners.Listener;

public interface ResourceService
{
	public <T extends Listener> void register(Class<T> type, Resource<T> resource);

	public <T extends Listener> void unregister(Class<T> type, Resource<T> resource);

	public Set<Resource<?>> registered();

	public <T extends Listener> Set<Resource<? extends T>> forListener(Class<T> type);
}
