package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;

interface Hook<U, T extends Listener> extends Listener
{
	public void add(U object);

	public void remove(U object);
	
	public Collection<U> objects();
	
	public T forListener();

	// TODO
//	public Collection<T> getObjects();
//	
//	public U getListener();
//	
//	public L forListener();
}
