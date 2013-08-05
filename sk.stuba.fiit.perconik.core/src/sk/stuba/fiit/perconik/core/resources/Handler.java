package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

interface Handler<T extends Listener>
{
	public void add(T listener);
	
	public void remove(T listener);
}
