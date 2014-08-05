package com.gratex.perconik.uaca;

import java.net.URL;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;

import static javax.ws.rs.client.Entity.entity;

public abstract class AbstractUacaProxy {
  protected static final Client client = ClientBuilder.newClient();

  protected static final Executor executor = PlatformExecutors.newLimitedThreadPool();

  protected AbstractUacaProxy() {
  }

  protected final void postRequest(final String path, @Nullable final Object request) {
    final Runnable command = new Runnable() {
      public final void run() {
        Response response = null;

        try {
          WebTarget target = target().path(path);

          beforeRequest(target, request);

          Entity<?> entity = entity(request, MediaType.APPLICATION_JSON_TYPE);

          response = target.request().post(entity);

          afterRequest(target, request, response);
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

  protected abstract void beforeRequest(WebTarget target, @Nullable Object request);

  protected abstract void afterRequest(WebTarget target, @Nullable Object request, Response response);

  protected abstract void reportFailure(String message, @Nullable Exception failure);

  protected WebTarget target() {
    return client.target(this.url().toString());
  }

  protected abstract URL url();
}
