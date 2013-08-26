package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.Provider;

public interface ListenerProvider extends Provider
{
	public <L extends Listener> L forClass(Class<L> type);
	
	public Class<? extends Listener> loadClass(String name) throws ClassNotFoundException;

	public Set<Class<? extends Listener>> classes();
	
	@Override
	public ListenerProvider parent();
	
	public interface Builder
	{
		public Builder add(Class<? extends Listener> type);
		
		public Builder addAll(Collection<Class<? extends Listener>> types);
		
		public Builder parent(ListenerProvider provider);
		
		public ListenerProvider build(); 
	}
}
