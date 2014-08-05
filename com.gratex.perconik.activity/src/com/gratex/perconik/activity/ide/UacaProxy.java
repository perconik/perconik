package com.gratex.perconik.activity.ide;

import java.net.URL;
import java.util.concurrent.Executor;

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

import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;
import com.gratex.perconik.services.uaca.ide.IdeCheckinEventData;
import com.gratex.perconik.services.uaca.ide.IdeCodeElementEventData;
import com.gratex.perconik.services.uaca.ide.IdeCodeElementEventType;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventData;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventType;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventData;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventType;
import com.gratex.perconik.services.uaca.ide.IdeFindEventData;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventData;
import com.gratex.perconik.services.uaca.ide.IdeProjectEventType;
import com.gratex.perconik.services.uaca.ide.IdeStateChangeEventData;

import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;

import static com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.isEventLoggerEnabled;

public final class UacaProxy {
  private static final URL url = UniformResources.newUrl("http://localhost:16375");

  private static final Client client = ClientBuilder.newClient();

  private static final Executor executor = PlatformExecutors.newLimitedThreadPool();

  private UacaProxy() {
    throw new AssertionError();
  }

  static final WebTarget newTarget() {
    return client.target(getActiveUrl().toString()).path("ide");
  }

  static <T> void postRequest(final String path, final T request) {
    final Runnable command = new Runnable() {
      public final void run() {
        Response response = null;

        try {
          WebTarget target = newTarget().path(path);

          if (isEventLoggerEnabled()) {
            logRequest(target, request);
          }

          response = target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));

          StatusType status = response.getStatusInfo();

          if (status.getFamily() == Family.CLIENT_ERROR || status.getFamily() == Family.SERVER_ERROR) {
            String message = String.format("POST %s returned %d %s", target.getUri(), status.getStatusCode(), status.getReasonPhrase());

            throw new IllegalStateException(message);
          }
        } catch (IllegalStateException | ProcessingException e) {
          reportFailure("Request to UACA failed", e);
        } finally {
          if (response != null) {
            response.close();
          }
        }
      }
    };

    executor.execute(command);
  }

  static final <T> void logRequest(final WebTarget target, final T request) {
    UacaReporter.logRequest(target, request);
  }

  static final void reportFailure(final String message, @Nullable final Exception failure) {
    UacaReporter.logError(message, failure);
    UacaReporter.displayError(message);
  }

  public static final void checkConnection(final String url) {
    client.target(url).path("ide/checkin").request().options().close();
  }

  public static final void checkConnection(final URL url) {
    checkConnection(url.toString());
  }

  public static final void sendCheckinEvent(final IdeCheckinEventData request) {
    postRequest("checkin", request);
  }

  public static final void sendCodeElementEvent(final IdeCodeElementEventData request, final IdeCodeElementEventType type) {
    postRequest("codeelement/" + type.urlPath(), request);
  }

  public static final void sendCodeEvent(final IdeCodeEventData request, final IdeCodeEventType type) {
    postRequest("code/" + type.urlPath(), request);
  }

  public static final void sendDocumentEvent(final IdeDocumentEventData request, final IdeDocumentEventType type) {
    postRequest("document/" + type.urlPath(), request);
  }

  public static final void sendFindEvent(final IdeFindEventData request) {
    postRequest("find", request);
  }

  public static final void sendProjectEvent(final IdeProjectEventData request, final IdeProjectEventType type) {
    postRequest("project/" + type.urlPath(), request);
  }

  public static final void sendStateChangeEvent(final IdeStateChangeEventData request) {
    postRequest("idestatechange", request);
  }

  public static final URL getActiveUrl() {
    return IdeActivityPreferences.getUacaUrl();
  }

  public static final URL getDefaultUrl() {
    return url;
  }
}
