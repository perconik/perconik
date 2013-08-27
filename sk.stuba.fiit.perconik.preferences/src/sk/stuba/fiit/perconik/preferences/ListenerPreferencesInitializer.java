package sk.stuba.fiit.perconik.preferences;

import sk.stuba.fiit.perconik.preferences.persistence.ListenerPersistenceData;

public final class ListenerPreferencesInitializer extends AbstractPreferencesInitializer
{
	public ListenerPreferencesInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		ListenerPreferences.getDefault().setListenerPersistenceData(ListenerPersistenceData.snapshot());
	}
}
