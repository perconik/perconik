package sk.stuba.fiit.perconik.core.services.listeners;

public class ListenerManagers
{
	private ListenerManagers()
	{
		throw new AssertionError();
	}
	
	public static final ListenerManager create()
	{
		return new StandardListenerManager();
	}
}
