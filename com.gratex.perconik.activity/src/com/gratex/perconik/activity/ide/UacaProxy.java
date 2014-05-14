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
import com.gratex.perconik.services.uaca.ide.dto.*;

public final class UacaProxy {
	private static final Client client = ClientBuilder.newClient();
	private static final Executor executor = Executors.newCachedThreadPool();
	
	public static void sendCheckinEvent(final IdeCheckinEventRequest req) {
		postAsync(req, "checkin");
	}

	public static void sendFindEvent(final IdeFindEventRequest req) {
		postAsync(req, "find");
	}

	public static void sendCodeEvent(final IdeCodeEventRequest req, final IdeCodeEventType type) {
		postAsync(req, "code/" + type.urlPath());
	}

	public static void sendDocumentEvent(final IdeDocumentEventRequest req, final IdeDocumentEventType type) {
		postAsync(req, "document/" + type.urlPath());
	}

	public static void sendProjectToEvent(final IdeProjectEventRequest req, final IdeProjectEventType type) {
		postAsync(req, "project/" + type.urlPath());
	}

	public static void sendIdeStateChangeEvent(final IdeStateChangeEventRequest req) {
		postAsync(req, "idestatechange");
	}

	public static void sendIdeCodeElementEvent(final IdeCodeElementEventRequest req, final IdeCodeElementEventType type) {
		postAsync(req, "codeelement/"+ type.urlPath());
	}

	public static void checkConnection(final String baseUrl) {
		client.target(baseUrl).path("ide/checkin").request().options().close();
	}
	
	private static <T> void postAsync(final T req, final String path) {
		final Runnable command = new Runnable(){
			public final void run()
			{
				WebTarget fullTarget = getRootIdeTarget().path(path);
				Response response = null;
				try
				{
					response = fullTarget.request().post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));

					if (IdeActivityPreferences.getPreferenceStore().getBoolean(Keys.logErrors))
					{
						StatusType status = response.getStatusInfo();
						if(status.getFamily() == Family.CLIENT_ERROR || status.getFamily() == Family.SERVER_ERROR){
							console.error("Request to Uaca failed. Details: POST %s returned %d %s%n", fullTarget.getUri().toString(), status.getStatusCode(), status.getReasonPhrase());
						}
					}
				}
				catch(ProcessingException ex){
					if (IdeActivityPreferences.getPreferenceStore().getBoolean(Keys.logErrors))
					{
						console.error(ex, "Request to Uaca failed");
					}	
				}
				finally
				{
					if(response!=null){
						response.close();
					}
				}
			}
		};
		
		executor.execute(command);
	}
	
	private static WebTarget getRootIdeTarget() {
		return client.target(IdeActivityPreferences.getWatcherServiceUrl().toString()).path("ide");
	}
}
