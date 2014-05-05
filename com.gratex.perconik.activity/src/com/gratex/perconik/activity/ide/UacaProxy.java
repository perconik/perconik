package com.gratex.perconik.activity.ide;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.gratex.perconik.services.uaca.ide.dto.*;

public final class UacaProxy {
	private static Client client = ClientBuilder.newClient();

	public static void sendCheckinEvent(IdeCheckinEventRequest req) {
		getRootIdeTarget().path("checkin").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendFindEvent(IdeFindEventRequest req) {
		getRootIdeTarget().path("find").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendCodeCutEvent(IdeCodeEventRequest req) {
		getRootIdeTarget().path("code/cut").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendCodeCopyEvent(IdeCodeEventRequest req) {
		getRootIdeTarget().path("code/copy").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendCodePasteEvent(IdeCodeEventRequest req) {
		getRootIdeTarget().path("code/paste").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendCodeSelectionChangedEvent(IdeCodeEventRequest req) {
		getRootIdeTarget().path("code/selectionchanged").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}
	
	public static void sendDocumentEvent(IdeDocumentEventRequest req, IdeDocumentOperationType type) {
		getRootIdeTarget().path("document/" + type.urlPath()).request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendDocumentSaveEvent(IdeDocumentEventRequest req) {
		getRootIdeTarget().path("document/save").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendProjectSwitchToEvent(IdeProjectEventRequest req) {
		getRootIdeTarget().path("project/switchto").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendProjectAddEvent(IdeProjectEventRequest req) {
		getRootIdeTarget().path("project/add").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendProjectRemoveEvent(IdeProjectEventRequest req) {
		getRootIdeTarget().path("project/remove").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	public static void sendIdeStateChangeEvent(IdeStateChangeEventRequest req) {
		getRootIdeTarget().path("idestatechange").request()
				.post(Entity.entity(req, MediaType.APPLICATION_JSON_TYPE));
	}

	private static WebTarget getRootIdeTarget() {
		// todo: from settings
		return client.target("http://localhost:16375/").path("ide");
	}
}
