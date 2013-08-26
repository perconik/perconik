package sk.stuba.fiit.perconik.core.services.listeners;

public class ListenerServices
{
	private ListenerServices()
	{
		throw new AssertionError();
	}
	
	public static final ListenerService create(final ListenerProvider provider, final ListenerManager manager)
	{
		return new StandardListenerService(provider, manager);
	}
}
