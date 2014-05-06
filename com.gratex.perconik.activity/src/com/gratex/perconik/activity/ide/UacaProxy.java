package com.gratex.perconik.activity.ide;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.gratex.perconik.services.uaca.ide.dto.*;

public final class UacaProxy {
	private static Client client = ClientBuilder.newClient();

	public static void sendCheckinEvent(final IdeCheckinEventRequest req) {
		getRootIdeTarget().path("checkin").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendFindEvent(final IdeFindEventRequest req) {
		getRootIdeTarget().path("find").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendCodeEvent(final IdeCodeEventRequest req, final IdeCodeEventType type) {
		getRootIdeTarget().path("code/" + type.urlPath()).request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendDocumentEvent(final IdeDocumentEventRequest req, final IdeDocumentEventType type) {
		getRootIdeTarget().path("document/" + type.urlPath()).request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendProjectToEvent(final IdeProjectEventRequest req, final IdeProjectEventType type) {
		getRootIdeTarget().path("project/" + type.urlPath()).request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendIdeStateChangeEvent(final IdeStateChangeEventRequest req) {
		getRootIdeTarget().path("idestatechange").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendIdeCodeElementEvent(final IdeCodeElementEventRequest req, final IdeCodeElementEventType type) {
		getRootIdeTarget().path("codeelement").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	private static WebTarget getRootIdeTarget() {
		// todo: from settings
		return client.target("http://localhost:16375/").path("ide");
	}
}
