package sk.stuba.fiit.perconik.core.listeners;

import java.util.Set;

public interface FilteringListener<T> extends Listener
{
	public Set<T> getEventTypes();
}
