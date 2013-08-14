package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Listener;

public interface ListenerProvider
{
	public <L extends Listener> L forClass(Class<L> type);
	
	public Class<? extends Listener> loadClass(String name) throws ClassNotFoundException;

	public Iterable<Class<? extends Listener>> loadedClasses();
}
