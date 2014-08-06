package com.gratex.perconik.uaca;

import java.net.URL;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;

import static javax.ws.rs.client.Entity.entity;

@SuppressWarnings({"static-method", "unused"})
public abstract class AbstractUacaProxy {
  private static final Client client = ClientBuilder.newClient();

  private static final Executor executor = PlatformExecutors.newLimitedThreadPool();

  protected AbstractUacaProxy() {
  }

  protected abstract URL url();

  protected final void send(final String path, @Nullable final Object request) {
    final Runnable command = new Runnable() {
      public final void run() {
        Response response = null;

        try {
          WebTarget target = createTarget().path(path);

          filterRequest(target, request);

          response = sendRequest(target, request);

          processResponse(target, request, response);
        } catch (Exception failure) {
          reportFailure("POST request to UACA failed", failure);
        } finally {
          if (response != null) {
            try {
              response.close();
            } catch (Exception e) {
              // ignore
            }
          }
        }
      }
    };

    executor.execute(command);
  }

  protected WebTarget createTarget() {
    return client.target(this.url().toString());
  }

  protected void filterRequest(WebTarget target, @Nullable Object request) {}

  protected Response sendRequest(WebTarget target, @Nullable Object request) {
    return target.request().post(entity(request, MediaType.APPLICATION_JSON_TYPE));
  }

  protected void processResponse(WebTarget target, @Nullable Object request, Response response) {}

  protected void reportFailure(String message, @Nullable Exception failure) {}
}
