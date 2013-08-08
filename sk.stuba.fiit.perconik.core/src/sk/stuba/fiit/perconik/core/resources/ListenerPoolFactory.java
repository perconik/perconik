package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

interface ListenerPoolFactory
{
	public <L extends Listener> Pool<L> create(Handler<L> handler);
}
