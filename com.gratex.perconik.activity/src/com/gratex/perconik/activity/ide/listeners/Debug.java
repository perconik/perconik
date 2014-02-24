package com.gratex.perconik.activity.ide.listeners;

import static sk.stuba.fiit.perconik.utilities.SmartStringBuilder.builder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.gratex.perconik.activity.ide.IdeApplication;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

final class Debug
{
	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private Debug()
	{
		throw new AssertionError();
	}

	static final boolean enabled()
	{
		return IdeApplication.getInstance().isDebug();
	}

	static final SmartStringBuilder message()
	{
		return builder().format(format, new Date()).appendln().tab();
	}
}
