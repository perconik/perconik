package com.gratex.perconik.uaca;

import java.net.URL;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import sk.stuba.fiit.perconik.data.providers.MapperProvider;

import static java.lang.String.format;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@SuppressWarnings({"static-method", "unused"})
public abstract class AbstractUacaProxy implements AutoCloseable {
  private final Client client;

  protected AbstractUacaProxy() {
    this.client = newClient().register(MapperProvider.class);
  }

  protected abstract Executor executor();

  protected abstract URL url();

  protected final void send(final String path, @Nullable final Object request) {
    final Runnable command = new Runnable() {
      public void run() {
        WebTarget target = null;
        Response response = null;

        try {
          target = createTarget().path(path);

          filterRequest(target, request);

          response = sendRequest(target, request);

          processResponse(target, request, response);
        } catch (Exception failure) {
          String uri = target != null ? target.getUri().toString() : "unknown";

          reportFailure(format("UacaProxy: POST %s -> Unexpected failure", uri), failure);
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

    this.executor().execute(command);
  }

  protected WebTarget createTarget() {
    return this.client.target(this.url().toString());
  }

  protected void filterRequest(final WebTarget target, @Nullable final Object request) {}

  protected Response sendRequest(final WebTarget target, @Nullable final Object request) {
    return target.request().post(entity(request, APPLICATION_JSON_TYPE));
  }

  protected void processResponse(final WebTarget target, @Nullable final Object request, final Response response) {}

  protected void reportFailure(final String message, @Nullable final Exception failure) {}

  public final void close() throws Exception {
    this.preClose();
    this.client.close();
    this.postClose();
  }

  protected void preClose() throws Exception {}

  protected void postClose() throws Exception {}
}
