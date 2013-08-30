package sk.stuba.fiit.perconik.core.persistence;

import sk.stuba.fiit.perconik.core.Listener;

public interface ListenerRegistration extends MarkableRegistration
{
	public Listener getListener();
	
	public Class<? extends Listener> getListenerClass();
}
