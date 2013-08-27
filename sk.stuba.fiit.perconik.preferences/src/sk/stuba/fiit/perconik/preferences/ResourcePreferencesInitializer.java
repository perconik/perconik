package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.preferences.persistence.ResourcePersistenceData;

public final class ResourcePreferencesInitializer extends AbstractPreferencesInitializer
{
	public ResourcePreferencesInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		Set<ResourcePersistenceData> data = ResourcePersistenceData.defaults();
		
		ResourcePreferences.getDefault().setResourcePersistenceData(data);
	}
}
