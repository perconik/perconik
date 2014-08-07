package com.gratex.perconik.uaca;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;

import static java.lang.String.format;

import static javax.ws.rs.client.Entity.entity;

@SuppressWarnings({"static-method", "unused"})
public abstract class AbstractUacaProxy implements Closeable {
  private static final Executor sharedExecutor = PlatformExecutors.newLimitedThreadPool();

  private final Client client;

  protected AbstractUacaProxy() {
    this.client = ClientBuilder.newClient();
  }

  protected abstract URL url();

  protected final void send(final String path, @Nullable final Object request) {
    final Runnable command = new Runnable() {
      public final void run() {
        Response response = null;
        WebTarget target = null;

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

    sharedExecutor.execute(command);
  }

  protected WebTarget createTarget() {
    return this.client.target(this.url().toString());
  }

  protected void filterRequest(WebTarget target, @Nullable Object request) {}

  protected Response sendRequest(WebTarget target, @Nullable Object request) {
    return target.request().post(entity(request, MediaType.APPLICATION_JSON_TYPE));
  }

  protected void processResponse(WebTarget target, @Nullable Object request, Response response) {}

  protected void reportFailure(String message, @Nullable Exception failure) {}

  public final void close() throws IOException {
    this.client.close();
    this.closeHook();
  }

  protected void closeHook() throws IOException {}
}
