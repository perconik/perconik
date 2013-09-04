package sk.stuba.fiit.perconik.preferences;

import com.google.common.collect.SetMultimap;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.services.resources.ResourceNamesSupplier;

public class RegisteredResourcesSupplier implements ResourceNamesSupplier
{
	public RegisteredResourcesSupplier()
	{
	}

	public final SetMultimap<Class<? extends Listener>, String> get()
	{
		ResourcePreferences preferences = ResourcePreferences.getInstance();
		
		return Registrations.toResourceNames(Registrations.marked(preferences.getResourcePersistenceData()));
	}
}
