package com.gratex.perconik.tag.prefs;

import com.gratex.perconik.tag.plugin.Activator;

public final class PrefKeys
{
	static final String prefix = Activator.PLUGIN_ID + ".preferences.";
	
	public static final String url = prefix + "url";
	
	public static final String profile = prefix + "profile";
			
	public static final String user = prefix + "user";
	
	public static final String checkConnection = prefix + "checkConnection";
	
	public static final String displayErrors = prefix + "displayErrors";
	
	private PrefKeys()
	{
	}
}
