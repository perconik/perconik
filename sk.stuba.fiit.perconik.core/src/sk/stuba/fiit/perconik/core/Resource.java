package sk.stuba.fiit.perconik.core;

import java.util.Collection;

public interface Resource<T extends Listener>
{
	public void register(T listener);
	
	public void unregister(T listener);
	
	public void unregisterAll(Class<? extends Listener> type);
	
	public <U extends Listener> Collection<U> registered(Class<U> type);
}
