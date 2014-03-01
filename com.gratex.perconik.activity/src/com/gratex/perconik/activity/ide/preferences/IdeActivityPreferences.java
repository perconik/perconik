package com.gratex.perconik.activity.ide.preferences;

import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferenceKeys.logErrors;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferenceKeys.logEvents;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferenceKeys.watcherLocalPart;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferenceKeys.watcherNamespace;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferenceKeys.watcherUrl;
import java.net.URL;
import javax.xml.namespace.QName;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;
import com.gratex.perconik.activity.ide.IdeActivityDefaults;
import com.gratex.perconik.activity.ide.plugin.Activator;

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
			
			store.setDefault(watcherUrl,       IdeActivityDefaults.watcherUrl.toString());
			store.setDefault(watcherNamespace, IdeActivityDefaults.watcherName.getNamespaceURI());
			store.setDefault(watcherLocalPart, IdeActivityDefaults.watcherName.getLocalPart());
		}
	}
	
	public static final IPreferenceStore getPreferenceStore()
	{
		return Activator.getDefault().getPreferenceStore();
	}
	
	public static final URL getWatcherServiceUrl()
	{
		IPreferenceStore store = getPreferenceStore();
		
		return UniformResources.newUrl(store.getString(watcherUrl));
	}
	
	public static final QName getWatcherServiceName()
	{
		IPreferenceStore store = getPreferenceStore();
		
		return new QName(store.getString(watcherNamespace), store.getString(watcherLocalPart));
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
