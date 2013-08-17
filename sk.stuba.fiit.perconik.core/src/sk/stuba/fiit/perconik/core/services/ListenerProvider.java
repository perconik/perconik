package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Provider;

public interface ListenerProvider extends Provider
{
	public <L extends Listener> L forClass(Class<L> type);
	
	public Class<? extends Listener> loadClass(String name) throws ClassNotFoundException;

	public Iterable<Class<? extends Listener>> classes();
}
