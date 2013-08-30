package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.ServiceFactory;

public interface ListenerServiceFactory extends ServiceFactory
{
	@Override
	public ListenerService create();
}
