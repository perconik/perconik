package sk.stuba.fiit.perconik.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.preferences.plugin.Activator;

public final class ListenersInitializer extends AbstractPreferenceInitializer
{
	static final String key = Activator.PLUGIN_ID + ".listeners"; 
	
	public ListenersInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		//store.setDefault(key, defaultObject);
	}
}
