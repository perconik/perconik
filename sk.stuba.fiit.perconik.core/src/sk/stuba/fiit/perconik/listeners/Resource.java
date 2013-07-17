package sk.stuba.fiit.perconik.listeners;

public interface Resource<T extends Listener>
{
	public void register(T listener);
	
	public void unregister(T listener);
	
	public void unregisterAll(Class<? super T> type);
}
