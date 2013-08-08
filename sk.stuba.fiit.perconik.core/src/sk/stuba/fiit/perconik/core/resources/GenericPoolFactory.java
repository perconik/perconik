package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

interface GenericPoolFactory
{
	public <L extends Listener> Pool<L> create(Handler<L> handler);
}
