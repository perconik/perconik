package sk.stuba.fiit.perconik.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;

/**
 * Used to aid in default resource preferences initialization.
 * 
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourcePreferencesInitializer extends AbstractPreferencesInitializer
{
	/**
	 * The constructor.
	 */
	public ResourcePreferencesInitializer()
	{
	}

	/**
	 * Called by the preference initializer to
	 * initialize default resource preferences.
	 * 
	 * <p><b>Warning:</b> Clients should not call this method.
	 * It will be called automatically by the preference initializer
	 * when the appropriate default preference node is accessed.
	 */
	@Override
	public final void initializeDefaultPreferences()
	{
		Set<ResourcePersistenceData> data = ResourcePersistenceData.defaults();
		
		ResourcePreferences.getDefault().setResourcePersistenceData(data);
	}
}
