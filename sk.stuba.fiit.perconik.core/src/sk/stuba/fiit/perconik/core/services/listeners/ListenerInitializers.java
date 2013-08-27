package sk.stuba.fiit.perconik.core.services.listeners;

public final class ListenerInitializers
{
	private ListenerInitializers()
	{
		throw new AssertionError();
	}
	
	public static final ListenerInitializer create()
	{
		return new StandardListenerInitializer();
	}
}
