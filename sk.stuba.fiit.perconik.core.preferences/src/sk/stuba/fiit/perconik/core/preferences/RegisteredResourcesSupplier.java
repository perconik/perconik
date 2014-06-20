package sk.stuba.fiit.perconik.core.preferences;

import com.google.common.collect.SetMultimap;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.services.resources.ResourceNamesSupplier;

/**
 * A class that supplies classes of currently registered
 * resources based on {@link ResourcePreferences}.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class RegisteredResourcesSupplier implements ResourceNamesSupplier
{
	/**
	 * The constructor.
	 */
	public RegisteredResourcesSupplier()
	{
	}

	/**
	 * Gets names of registered resources grouped under listener
	 * types according to the current state of <i>instance</i> scope
	 * of {@code ResourcePreferences}.
	 * @return names of registered resources grouped under listener types  
	 */
	public final SetMultimap<Class<? extends Listener>, String> get()
	{
		ResourcePreferences preferences = ResourcePreferences.getInstance();
		
		return Registrations.toResourceNames(Registrations.marked(preferences.getResourcePersistenceData()));
	}
}
