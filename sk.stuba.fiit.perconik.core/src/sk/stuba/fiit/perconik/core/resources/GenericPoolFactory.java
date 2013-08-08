package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

interface GenericPoolFactory
{
	public <T extends Listener> Pool<T> create(Handler<T> handler);
}
