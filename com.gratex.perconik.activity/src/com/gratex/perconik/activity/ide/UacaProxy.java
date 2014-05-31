
package com.gratex.perconik.activity.ide;

import static com.gratex.perconik.activity.ide.Internals.console;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys;
import com.gratex.perconik.activity.ide.ui.IdeActivityMessageDialogs;
import com.gratex.perconik.services.uaca.ide.IdeCheckinEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeCodeElementEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeFindEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeStateChangeEventRequest;
import com.gratex.perconik.services.uaca.ide.type.IdeCodeElementEventType;
import com.gratex.perconik.services.uaca.ide.type.IdeCodeEventType;
import com.gratex.perconik.services.uaca.ide.type.IdeDocumentEventType;
import com.gratex.perconik.services.uaca.ide.type.IdeProjectEventType;

public final class UacaProxy
{
	private static final URL url = UniformResources.newUrl("http://localhost:16375");
	
	private static final Client client = ClientBuilder.newClient();
	
	private static final Executor executor = Executors.newCachedThreadPool();

	private UacaProxy()
	{
		throw new AssertionError();
	}
	
	static final WebTarget newIdeTarget()
	{
		return client.target(getActiveUrl().toString()).path("ide");
	}

	static <T> void postAsync(final T request, final String path)
	{
		final Runnable command = new Runnable()
		{
			public final void run()
			{
				Response response = null;
				
				try
				{
					WebTarget target = newIdeTarget().path(path);
					
					response = target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
	
					StatusType status = response.getStatusInfo();
					
					if (status.getFamily() == Family.CLIENT_ERROR || status.getFamily() == Family.SERVER_ERROR)
					{
						reportFailure(String.format("Request to Uaca failed%n%nPOST %s returned %d %s%n", target.getUri(), status.getStatusCode(), status.getReasonPhrase()), null);
					}
				}
				catch (ProcessingException e)
				{
					reportFailure("Request to UACA failed", e);
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
	
	static final void reportFailure(final String message, @Nullable final Exception failure)
	{
		IPreferenceStore store = IdeActivityPreferences.getPreferenceStore();
	
		if (store.getBoolean(Keys.logErrors))
		{
			console.error(message, failure);
		}
		
		if (store.getBoolean(Keys.displayErrors))
		{
			IdeActivityMessageDialogs.openError(Keys.displayErrors, message);
		}
	}

	public static final void checkConnection(final String url)
	{
		client.target(url).path("ide/checkin").request().options().close();
	}

	public static final void checkConnection(final URL url)
	{
		checkConnection(url.toString());
	}

	public static final void sendCheckinEvent(final IdeCheckinEventRequest request)
	{
		postAsync(request, "checkin");
	}

	public static final void sendCodeElementEvent(final IdeCodeElementEventRequest request, final IdeCodeElementEventType type)
	{
		postAsync(request, "codeelement/" + type.urlPath());
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

	public static final void sendProjectEvent(final IdeProjectEventRequest request, final IdeProjectEventType type)
	{
		postAsync(request, "project/" + type.urlPath());
	}

	public static final void sendStateChangeEvent(final IdeStateChangeEventRequest request)
	{
		postAsync(request, "idestatechange");
	}

	public static final URL getActiveUrl()
	{
		return IdeActivityPreferences.getUacaUrl();
	}

	public static final URL getDefaultUrl()
	{
		return url;
	}
}
