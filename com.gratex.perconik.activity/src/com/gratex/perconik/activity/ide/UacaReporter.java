package com.gratex.perconik.activity.ide;

import javax.annotation.Nullable;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.databind.ObjectWriter;

import com.gratex.perconik.activity.ObjectMappers;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys;
import com.gratex.perconik.activity.ide.ui.IdeActivityMessageDialogs;

import static com.gratex.perconik.activity.ide.Internals.console;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.getPreferenceStore;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.isErrorLoggerEnabled;
import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.isEventLoggerEnabled;

final class UacaReporter
{
	private static final ObjectWriter writer = ObjectMappers.getDefault().writerWithDefaultPrettyPrinter();
	
	private UacaReporter()
	{
		throw new AssertionError();
	}
	
	static final <T> void logRequest(final WebTarget target, final T request)
	{
		if (!isEventLoggerEnabled())
		{
			return;
		}
		
		try
		{
			String message = String.format("%s%n%s", target.getUri(), writer.writeValueAsString(request));
			
			Internals.console.notice(message);
		}
		catch (Exception e)
		{
			UacaProxy.reportFailure("Event logger failed", e);
		}
	}
	
	static final void logError(final String message, @Nullable final Exception failure)
	{
		if (isErrorLoggerEnabled())
		{
			console.error(failure, message);
		}
	}
	
	static final void displayError(final String message)
	{
		if (getPreferenceStore().getBoolean(Keys.displayErrors))
		{
			IdeActivityMessageDialogs.openError(Keys.displayErrors, message);
		}
	}
}
