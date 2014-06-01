package com.gratex.perconik.activity.ide.listeners;

import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.isEventLoggerEnabled;
import static sk.stuba.fiit.perconik.utilities.SmartStringBuilder.builder;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

final class Log
{
	private Log()
	{
		throw new AssertionError();
	}

	static final boolean enabled()
	{
		return isEventLoggerEnabled();
	}

	static final SmartStringBuilder message()
	{
		return builder();
	}
}
