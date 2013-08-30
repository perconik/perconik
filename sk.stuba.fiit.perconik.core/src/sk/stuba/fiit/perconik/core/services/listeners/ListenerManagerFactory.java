package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.ManagerFactory;

public interface ListenerManagerFactory extends ManagerFactory
{
	@Override
	public ListenerManager create();
}
