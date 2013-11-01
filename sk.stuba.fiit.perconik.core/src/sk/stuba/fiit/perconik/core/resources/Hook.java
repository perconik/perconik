package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.annotations.Internal;

@Internal
interface Hook<T, L extends Listener> extends Listener
{
	public void add(T object);

	public void remove(T object);
	
	public Collection<T> toCollection();
	
	public L forListener();
}
