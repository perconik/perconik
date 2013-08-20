package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.Service;

public interface ListenerService extends Service
{
	public ListenerProvider getListenerProvider();
	
	public ListenerManager getListenerManager();
}
