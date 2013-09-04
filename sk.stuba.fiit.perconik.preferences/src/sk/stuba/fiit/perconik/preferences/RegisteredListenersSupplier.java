package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;

public class RegisteredListenersSupplier implements ListenerClassesSupplier
{
	public RegisteredListenersSupplier()
	{
	}

	public final Set<Class<? extends Listener>> get()
	{
		ListenerPreferences preferences = ListenerPreferences.getInstance();
		
		return Registrations.toListenerClasses(Registrations.marked(preferences.getListenerPersistenceData()));
	}
}
