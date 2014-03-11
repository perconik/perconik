package sk.stuba.fiit.perconik.activity.ide.preferences;

import sk.stuba.fiit.perconik.activity.plugin.Activator;

public final class IdeActivityPreferenceKeys
{
	static final String prefix = Activator.PLUGIN_ID + ".preferences";

	public static final String logErrors = prefix + ".log.error";
	
	public static final String logEvents = prefix + ".log.event";
	
	private IdeActivityPreferenceKeys()
	{
		throw new AssertionError();
	}
}
