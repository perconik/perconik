package sk.stuba.fiit.perconik.core.resources;

interface Handler<T>
{
	public void register(T object);
	
	public void unregister(T object);
}
