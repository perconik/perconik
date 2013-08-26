package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider.Builder;

public class ListenerProviders
{
	private ListenerProviders()
	{
		throw new AssertionError();
	}
	
	public static final ListenerProvider getSystemProvider()
	{
		return SystemListenerProvider.getInstance();
	}

	public static final Builder builder()
	{
		return StandardListenerProvider.builder();
	}
}
