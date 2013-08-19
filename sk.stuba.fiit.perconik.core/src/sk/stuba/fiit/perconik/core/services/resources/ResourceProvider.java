package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Provider;
import sk.stuba.fiit.perconik.core.Resource;

public interface ResourceProvider extends Provider
{
	public Resource<?> forName(String name);
	
	public <L extends Listener> Set<Resource<? super L>> forClass(Class<L> type);
	
	public Iterable<String> names();
	
	public Iterable<Class<? extends Listener>> classes();
	
	public Iterable<Resource<?>> resources();

	public interface Builder
	{
		public <L extends Listener> Builder add(final Class<L> type, final Resource<L> resource);
		
		public ResourceProvider build(); 
	}
}
