package sk.stuba.fiit.perconik.preferences;

import sk.stuba.fiit.perconik.preferences.persistence.ResourcePersistenceData;

public final class ResourcePreferencesInitializer extends AbstractPreferencesInitializer
{
	public ResourcePreferencesInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		ResourcePreferences.getDefault().setResourcePersistenceData(ResourcePersistenceData.snapshot());
	}
}
