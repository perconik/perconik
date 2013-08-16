package sk.stuba.fiit.perconik.core;

import java.util.Collection;

public interface Resource<L extends Listener> extends Registrable
{
	public void register(L listener);
	
	public void unregister(L listener);
	
	public void unregisterAll(Class<? extends Listener> type);
	
	public <U extends Listener> Collection<U> registered(Class<U> type);
}
