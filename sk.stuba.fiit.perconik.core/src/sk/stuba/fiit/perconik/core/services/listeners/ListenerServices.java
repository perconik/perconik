package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerService.Builder;

public final class ListenerServices
{
	private ListenerServices()
	{
		throw new AssertionError();
	}
	
	public static final Builder builder()
	{
		return StandardListenerService.builder();
	}
}
