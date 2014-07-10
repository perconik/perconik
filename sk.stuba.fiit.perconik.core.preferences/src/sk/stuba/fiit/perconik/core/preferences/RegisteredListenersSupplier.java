package sk.stuba.fiit.perconik.core.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;

/**
 * A class that supplies classes of currently registered
 * listeners based on {@link ListenerPreferences}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class RegisteredListenersSupplier implements ListenerClassesSupplier
{
	/**
	 * The constructor.
	 */
	public RegisteredListenersSupplier()
	{
	}

	/**
	 * Gets classes of registered listeners according to the current
	 * state of <i>instance</i> scope of {@code ListenerPreferences}.
	 * @return classes of registered listeners
	 */
	public final Set<Class<? extends Listener>> get()
	{
		ListenerPreferences preferences = ListenerPreferences.getConfiguration();

		return Registrations.toListenerClasses(Registrations.marked(preferences.getListenerPersistenceData()));
	}
}
