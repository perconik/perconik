package com.gratex.perconik.activity.ide.preferences;

import com.gratex.perconik.activity.ide.plugin.Activator;

public final class IdeActivityPreferenceKeys
{
	static final String prefix = Activator.PLUGIN_ID + ".preferences";

	public static final String logErrors = prefix + ".logger.error";
	
	public static final String logEvents = prefix + ".logger.event";
	
	public static final String watcherUrl = prefix + ".watcher.url";
	
	public static final String watcherNamespace = prefix + ".watcher.namespace";
	
	public static final String watcherLocalPart = prefix + ".watcher.local";
	
	private IdeActivityPreferenceKeys()
	{
		throw new AssertionError();
	}
}
