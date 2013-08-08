package sk.stuba.fiit.perconik.core;

import java.util.Collection;

public interface Resource<T extends Listener>
{
	public void register(T listener);
	
	public void unregister(T listener);
	
	public void unregisterAll(Class<? extends Listener> type);
	
	public <U extends Listener> Collection<U> registered(Class<U> type);
	
	// TODO
	// add register hooks
	//public void preRegister(final Class<? extends T> type, final Resource<T> resource);
	
	// TODO
	// consider in resource service
	//public <T extends Listener> void register(Class<? super T> type, Resource<T> resource);
	//public <T extends Listener> void register(Class<T> type, Resource<? extends T> resource);
}
