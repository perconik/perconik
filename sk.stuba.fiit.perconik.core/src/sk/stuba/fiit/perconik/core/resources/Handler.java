package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

interface Handler<L extends Listener>
{
	public void add(L listener);
	
	public void remove(L listener);
}
