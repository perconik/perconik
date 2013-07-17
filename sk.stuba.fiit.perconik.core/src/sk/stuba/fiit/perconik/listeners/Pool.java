package sk.stuba.fiit.perconik.listeners;

import java.util.Collection;

interface Pool<T extends Listener>
{
	public boolean contains(T listener);
	
	public void add(T listener);
	
	public void remove(T listener);
	
	public Collection<T> toCollection();
}
