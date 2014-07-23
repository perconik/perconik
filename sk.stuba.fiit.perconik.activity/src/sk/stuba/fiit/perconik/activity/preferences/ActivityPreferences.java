package sk.stuba.fiit.perconik.activity.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import sk.stuba.fiit.perconik.activity.plugin.Activator;

import static sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences.Keys.logErrors;
import static sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences.Keys.logEvents;

public abstract class ActivityPreferences
{
	protected final IPreferenceStore store;
	
	protected ActivityPreferences()
	{
		this.store = Activator.getDefault().getPreferenceStore();
	}
	
	protected void initialize()
	{
		this.store.setDefault(logErrors, true);
		this.store.setDefault(logEvents, false);
	}
	
	protected static class Keys
	{
		static final String prefix = Activator.PLUGIN_ID + ".preferences";

		public static final String logErrors = prefix + ".log.errors";
		
		public static final String logEvents = prefix + ".log.events";
		
		protected Keys()
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
