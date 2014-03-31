package sk.stuba.fiit.perconik.activity.preferences;

import static sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences.Keys.logErrors;
import static sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences.Keys.logEvents;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.activity.plugin.Activator;

public final class ActivityPreferences
{
	private static final ActivityPreferences instance = new ActivityPreferences();
	
	private final IPreferenceStore store;
	
	private ActivityPreferences()
	{
		this.store = Activator.getDefault().getPreferenceStore();
	}
	
	final void initialize()
	{
		this.store.setDefault(logErrors, true);
		this.store.setDefault(logEvents, false);
	}
	
	public static final ActivityPreferences getInstance()
	{
		return instance;
	}
	
	public static final class Initializer extends AbstractPreferenceInitializer
	{
		public Initializer()
		{
		}

		@Override
		public final void initializeDefaultPreferences()
		{
			ActivityPreferences.getInstance().initialize();
		}
	}
	
	public static final class Keys
	{
		static final String prefix = Activator.PLUGIN_ID + ".preferences";

		public static final String logErrors = prefix + ".log.errors";
		
		public static final String logEvents = prefix + ".log.events";
		
		private Keys()
		{
			throw new AssertionError();
		}
	}
	
	public final IPreferenceStore getPreferenceStore()
	{
		return this.store;
	}

	public final boolean isErrorLoggerEnabled()
	{
		return this.getPreferenceStore().getBoolean(logErrors);
	}
	
	public final boolean isEventLoggerEnabled()
	{
		return this.getPreferenceStore().getBoolean(logEvents);
	}
}
