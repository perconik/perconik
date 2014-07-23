package com.gratex.perconik.activity.ide.listeners;

import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;

import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

final class Log
{
	private Log()
	{
		throw new AssertionError();
	}

	static final boolean enabled()
	{
		return IdeActivityPreferences.isEventLoggerEnabled();
	}

	static final SmartStringBuilder message()
	{
		return SmartStringBuilder.builder();
	}
}
