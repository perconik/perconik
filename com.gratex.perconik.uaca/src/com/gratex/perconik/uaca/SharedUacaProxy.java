package com.gratex.perconik.uaca;

import java.net.URL;

import javax.annotation.Nullable;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import com.gratex.perconik.uaca.preferences.UacaPreferences;

public class SharedUacaProxy extends AbstractUacaProxy {
  public SharedUacaProxy() {
  }

  public static final void checkConnection(final String url) {
    ClientBuilder.newClient().target(url).path("ide/checkin").request().options().close();
  }

  public static final void checkConnection(final URL url) {
    checkConnection(url.toString());
  }

  @Override
  protected void beforeRequest(WebTarget target, @Nullable Object request) {
    UacaReporter.logRequest(target, request);
  }

  @Override
  protected void afterRequest(WebTarget target, @Nullable Object request, Response response) {
    StatusType status = response.getStatusInfo();

    if (status.getFamily() == Family.CLIENT_ERROR || status.getFamily() == Family.SERVER_ERROR) {
      String message = String.format("POST %s returned %d %s", target.getUri(), status.getStatusCode(), status.getReasonPhrase());

      throw new IllegalStateException(message);
    }
  }

  @Override
  protected void reportFailure(String message, Exception failure) {
    UacaReporter.logError(message, failure);
    UacaReporter.displayError(message);
  }

  @Override
  protected URL url() {
    return UacaPreferences.getShared().getUacaUrl();
  }
}
