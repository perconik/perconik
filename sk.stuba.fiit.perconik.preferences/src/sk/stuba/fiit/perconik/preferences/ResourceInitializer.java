package sk.stuba.fiit.perconik.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.preferences.plugin.Activator;

public final class ResourceInitializer extends AbstractPreferenceInitializer
{
	static final String key = Activator.PLUGIN_ID + ".resources";
	
	static final ResourceStore store = new ResourceStore(Activator.getDefault().getPreferenceStore(), key);
	
	public ResourceInitializer()
	{
	}

	@Override
	public final void initializeDefaultPreferences()
	{
		store.setDefault(Services.getResourceService().getResourceProvider().names());
	}
}
