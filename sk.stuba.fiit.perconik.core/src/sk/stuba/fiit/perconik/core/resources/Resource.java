package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.listeners.Listener;

public interface Resource<T extends Listener>
{
	public void register(T listener);
	
	public void unregister(T listener);
	
	public void unregisterAll(Class<? extends Listener> type);
	
	public <U extends Listener> Collection<U> getRegistered(Class<U> type);
}
