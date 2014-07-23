package sk.stuba.fiit.perconik.core.resources;

interface PoolFactory
{
	public <T> Pool<T> create(Handler<T> handler);
}
