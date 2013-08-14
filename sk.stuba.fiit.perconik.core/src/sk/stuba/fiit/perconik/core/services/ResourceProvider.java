package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Resource;

public interface ResourceProvider
{
	public Resource<?> forName(String name);
	
	public Iterable<Resource<?>> resources();
}
