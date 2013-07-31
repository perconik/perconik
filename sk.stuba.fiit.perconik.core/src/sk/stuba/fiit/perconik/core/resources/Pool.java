package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.listeners.Listener;

interface Pool<T extends Listener>
{
	public boolean contains(T listener);
	
	public void add(T listener);
	
	public void remove(T listener);
	
	public Collection<T> toCollection();
}
