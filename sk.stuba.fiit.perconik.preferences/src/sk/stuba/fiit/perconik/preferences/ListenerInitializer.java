package sk.stuba.fiit.perconik.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.preferences.plugin.Activator;

public final class ListenerInitializer extends AbstractPreferenceInitializer
{
	static final String key = Activator.PLUGIN_ID + ".listeners";
	
	static final ListenerStore store = new ListenerStore(Activator.getDefault().getPreferenceStore(), key);
	
	public ListenerInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		store.setDefault(Services.getListenerService().getListenerProvider().classes());
	}
}
