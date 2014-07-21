package com.gratex.perconik.activity.ide.preferences;

import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.gratex.perconik.activity.ide.UacaProxy;
import com.gratex.perconik.activity.plugin.Activator;

import sk.stuba.fiit.perconik.utilities.net.UniformResources;

import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys.checkConnection;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys.displayErrors;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys.logErrors;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys.logEvents;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys.uacaUrl;

public final class IdeActivityPreferences
{
	private static final AtomicReference<IPreferenceStore> store = new AtomicReference<>();
	
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
			
			store.setDefault(checkConnection, true);
			store.setDefault(displayErrors, true);

			store.setDefault(logErrors, true);
			store.setDefault(logEvents, false);
			
			store.setDefault(uacaUrl, UacaProxy.getDefaultUrl().toString());
		}
	}
	
	public static final class Keys
	{
		static final String prefix = Activator.PLUGIN_ID + ".preferences.";

		public static final String checkConnection = prefix + "checkConnection";

		public static final String displayErrors = prefix + "displayErrors";

		public static final String logErrors = prefix + "log.errors";
		
		public static final String logEvents = prefix + "log.events";
		
		public static final String uacaUrl = prefix + "uaca.url";
		
		private Keys()
		{
			throw new AssertionError();
		}
	}
	
	public static final IPreferenceStore getPreferenceStore()
	{
		Activator activator = Activator.getDefault();
		
		if (activator != null)
		{
			store.set(activator.getPreferenceStore());
		}
		
		return store.get();
	}
	
	public static final URL getUacaUrl()
	{
		IPreferenceStore store = getPreferenceStore();
		
		return UniformResources.newUrl(store.getString(uacaUrl));
	}
	
	public static final boolean isErrorLoggerEnabled()
	{
		return getPreferenceStore().getBoolean(logErrors);
	}
	
	public static final boolean isEventLoggerEnabled()
	{
		return getPreferenceStore().getBoolean(logEvents);
	}
}
