package sk.stuba.fiit.perconik.preferences;

import sk.stuba.fiit.perconik.preferences.persistence.ResourcePersistenceData;

public final class ResourceInitializer extends AbstractInitializer
{
	public ResourceInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		ResourcePreferences.getDefault().setResourcePersistenceData(ResourcePersistenceData.snapshot());
	}
}
