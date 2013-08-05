package sk.stuba.fiit.perconik.core;

import java.util.Set;

public interface FilteringListener<T> extends Listener
{
	public Set<T> getEventTypes();
}
