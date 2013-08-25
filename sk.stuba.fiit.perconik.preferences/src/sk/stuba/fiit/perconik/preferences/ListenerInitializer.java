package sk.stuba.fiit.perconik.preferences;

import sk.stuba.fiit.perconik.preferences.persistence.ListenerPersistenceData;

public final class ListenerInitializer extends AbstractInitializer
{
	public ListenerInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		ListenerPreferences.getDefault().setListenerPersistenceData(ListenerPersistenceData.snapshot());
	}
}
