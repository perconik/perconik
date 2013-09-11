package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;

/**
 * Used to aid in default listener preferences initialization.
 * 
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerPreferencesInitializer extends AbstractPreferencesInitializer
{
	/**
	 * The constructor.
	 */
	public ListenerPreferencesInitializer()
	{
	}

	/**
	 * Called by the preference initializer to
	 * initialize default listener preferences.
	 * 
	 * <p><b>Warning:</b> Clients should not call this method.
	 * It will be called automatically by the preference initializer
	 * when the appropriate default preference node is accessed.
	 */
	@Override
	public final void initializeDefaultPreferences()
	{
		Set<ListenerPersistenceData> data = ListenerPersistenceData.defaults();
		
		ListenerPreferences.getDefault().setListenerPersistenceData(data);
	}
}
