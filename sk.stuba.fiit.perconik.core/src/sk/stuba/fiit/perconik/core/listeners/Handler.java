package sk.stuba.fiit.perconik.core.listeners;

interface Handler<T extends Listener>
{
	public void add(T listener);
	
	public void remove(T listener);
}
