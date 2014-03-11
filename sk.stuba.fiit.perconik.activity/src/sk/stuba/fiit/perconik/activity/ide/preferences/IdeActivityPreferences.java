package sk.stuba.fiit.perconik.activity.ide.preferences;

import static sk.stuba.fiit.perconik.activity.ide.preferences.IdeActivityPreferenceKeys.logErrors;
import static sk.stuba.fiit.perconik.activity.ide.preferences.IdeActivityPreferenceKeys.logEvents;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.activity.plugin.Activator;

public final class IdeActivityPreferences
{
	private IdeActivityPreferences()
	{
		throw new AssertionError();
	}
	
	public static final class Initializer extends AbstractPreferenceInitializer
	{
		public Initializer()
		{
		}

		@Override
		public final void initializeDefaultPreferences()
		{
			IPreferenceStore store = getPreferenceStore();
			
			store.setDefault(logErrors, true);
			store.setDefault(logEvents, false);
		}
	}
	
	public static final IPreferenceStore getPreferenceStore()
	{
		return Activator.getDefault().getPreferenceStore();
	}
	
	public static final boolean isEventLoggerEnabled()
	{
		return getPreferenceStore().getBoolean(logEvents);
	}
}
