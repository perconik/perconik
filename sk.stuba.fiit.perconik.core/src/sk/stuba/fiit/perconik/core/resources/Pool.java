package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;

interface Pool<L extends Listener>
{
	public boolean contains(L listener);
	
	public void add(L listener);
	
	public void remove(L listener);
	
	public Collection<L> toCollection();
}
