package sk.stuba.fiit.perconik.listeners;

interface PoolFactory
{
	public <T extends Listener> Pool<T> create(Handler<T> handler);
}
