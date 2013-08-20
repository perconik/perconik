package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.Provider;

public interface ListenerProvider extends Provider
{
	public <L extends Listener> L forClass(Class<L> type);
	
	public Class<? extends Listener> loadClass(String name) throws ClassNotFoundException;

	public Iterable<Class<? extends Listener>> classes();
	
	public interface Builder
	{
		public Builder add(Class<? extends Listener> type);
		
		public Builder addAll(Collection<Class<? extends Listener>> types);
		
		public ListenerProvider build(); 
	}
}
