package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.Provider;

public interface ResourceProvider extends Provider
{
	public Resource<?> forName(String name);
	
	public <L extends Listener> Set<Resource<? super L>> forType(Class<L> type);
	
	public Iterable<String> names();
	
	public Iterable<Class<? extends Listener>> types();
	
	public Iterable<Resource<?>> resources();

	public interface Builder
	{
		public <L extends Listener> Builder add(Class<L> type, Resource<L> resource);
		
		public ResourceProvider build(); 
	}
}
