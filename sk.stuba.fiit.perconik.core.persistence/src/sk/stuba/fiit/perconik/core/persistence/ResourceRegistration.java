package sk.stuba.fiit.perconik.core.persistence;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

public interface ResourceRegistration extends MarkableRegistration
{
	public Class<? extends Listener> getListenerType();

	public Resource<?> getResource();
	
	public String getResourceName();
}
