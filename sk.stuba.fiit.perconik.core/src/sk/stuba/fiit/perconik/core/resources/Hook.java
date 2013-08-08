package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

interface Hook<U, T extends Listener> extends Listener
{
	public void add(U object);

	public void remove(U object);
	
	public T forListener();
}
