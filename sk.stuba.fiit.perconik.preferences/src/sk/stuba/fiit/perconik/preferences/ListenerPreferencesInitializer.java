package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;

public final class ListenerPreferencesInitializer extends AbstractPreferencesInitializer
{
	public ListenerPreferencesInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		Set<ListenerPersistenceData> data = ListenerPersistenceData.defaults();
		
		ListenerPreferences.getDefault().setListenerPersistenceData(data);
	}
}
