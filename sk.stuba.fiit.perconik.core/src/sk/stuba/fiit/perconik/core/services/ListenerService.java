package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Service;

public interface ListenerService extends Service
{
	public ListenerProvider getListenerProvider();
	
	public ListenerManager getListenerManager();
}
