package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;

interface Pool<T>
{
	public boolean contains(T object);
	
	public void add(T object);
	
	public void remove(T object);
	
	public Collection<T> toCollection();
}
