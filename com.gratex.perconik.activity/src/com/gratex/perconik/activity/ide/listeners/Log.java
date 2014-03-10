package com.gratex.perconik.activity.ide.listeners;

import static sk.stuba.fiit.perconik.utilities.SmartStringBuilder.builder;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import com.gratex.perconik.activity.ide.IdeApplication;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;

final class Log
{
	private Log()
	{
		throw new AssertionError();
	}

	static final boolean enabled()
	{
		return IdeApplication.getInstance().isDebug() || IdeActivityPreferences.isEventLoggerEnabled();
	}

	static final SmartStringBuilder message()
	{
		return builder();
	}
}
