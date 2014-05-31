
package com.gratex.perconik.activity.ide;

import static com.gratex.perconik.activity.ide.Internals.console;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys;
import com.gratex.perconik.services.uaca.ide.*;
import com.gratex.perconik.services.uaca.ide.type.IdeCodeElementEventType;
import com.gratex.perconik.services.uaca.ide.type.IdeCodeEventType;
import com.gratex.perconik.services.uaca.ide.type.IdeDocumentEventType;
import com.gratex.perconik.services.uaca.ide.type.IdeProjectEventType;

public final class UacaProxy
{
	private static final Client client = ClientBuilder.newClient();
	
	private static final Executor executor = Executors.newCachedThreadPool();

	private UacaProxy()
	{
		throw new AssertionError();
	}
	
	private static <T> void postAsync(final T request, final String path)
	{
		final Runnable command = new Runnable()
		{
			public final void run()
			{
				WebTarget target = getRootIdeTarget().path(path);
				
				Response response = null;
				
				try
				{
					response = target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
	
					if (IdeActivityPreferences.getPreferenceStore().getBoolean(Keys.logErrors))
					{
						StatusType status = response.getStatusInfo();
						
						if (status.getFamily() == Family.CLIENT_ERROR || status.getFamily() == Family.SERVER_ERROR)
						{
							console.error("Request to Uaca failed. Details: POST %s returned %d %s%n", target.getUri(), status.getStatusCode(), status.getReasonPhrase());
						}
					}
				}
				catch (ProcessingException e)
				{
					if (IdeActivityPreferences.getPreferenceStore().getBoolean(Keys.logErrors))
					{
						console.error(e, "Request to UACA failed");
					}
				}
				finally
				{
					if (response != null)
					{
						response.close();
					}
				}
			}
		};
	
		executor.execute(command);
	}

	static final WebTarget getRootIdeTarget()
	{
		return client.target(IdeActivityPreferences.getWatcherServiceUrl().toString()).path("ide");
	}

	public static final void checkConnection(final String baseUrl)
	{
		client.target(baseUrl).path("ide/checkin").request().options().close();
	}

	public static final void sendCheckinEvent(final IdeCheckinEventRequest request)
	{
		postAsync(request, "checkin");
	}

	public static final void sendCodeEvent(final IdeCodeEventRequest request, final IdeCodeEventType type)
	{
		postAsync(request, "code/" + type.urlPath());
	}

	public static final void sendDocumentEvent(final IdeDocumentEventRequest request, final IdeDocumentEventType type)
	{
		postAsync(request, "document/" + type.urlPath());
	}

	public static final void sendFindEvent(final IdeFindEventRequest request)
	{
		postAsync(request, "find");
	}

	public static final void sendIdeStateChangeEvent(final IdeStateChangeEventRequest request)
	{
		postAsync(request, "idestatechange");
	}

	public static final void sendIdeCodeElementEvent(final IdeCodeElementEventRequest request, final IdeCodeElementEventType type)
	{
		postAsync(request, "codeelement/" + type.urlPath());
	}

	public static final void sendProjectToEvent(final IdeProjectEventRequest request, final IdeProjectEventType type)
	{
		postAsync(request, "project/" + type.urlPath());
	}
}
