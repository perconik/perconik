package com.gratex.perconik.activity.ide;

import javax.ws.rs.client.WebTarget;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gratex.perconik.activity.ObjectMappers;

final class UacaRequestLogger
{
	static final ObjectWriter writer = ObjectMappers.getDefault().writerWithDefaultPrettyPrinter();
	
	private UacaRequestLogger()
	{
		throw new AssertionError();
	}
	
	static final <T> void log(final WebTarget target, final T request)
	{
		try
		{
			SmartStringBuilder builder = new SmartStringBuilder(2048);
			
			builder.format("URI: %s%n%n", target.getUri());
			builder.format("Request:%n%s%n", writer.writeValueAsString(request));
			
			Internals.console.notice(builder.toString());
		}
		catch (Exception e)
		{
			UacaProxy.reportFailure("Event logger failed", e);
		}
	}
}
