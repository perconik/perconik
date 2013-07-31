package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.Listener;

interface PoolFactory
{
	public <T extends Listener> Pool<T> create(Handler<T> handler);
}
