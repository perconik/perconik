package sk.stuba.fiit.perconik.core.services;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

public interface ResourceProvider
{
	public Resource<?> forName(String name);
	
	public <L extends Listener> Set<Resource<? super L>> forClass(Class<L> type);
	
	public Iterable<String> names();
	
	public Iterable<Class<? extends Listener>> classes();
	
	public Iterable<Resource<?>> resources();
}
