package com.gratex.perconik.uaca;

import java.net.URL;

import javax.annotation.Nullable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static java.lang.String.format;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import static com.google.common.base.Throwables.propagate;

public abstract class AbstractUacaProxy implements UacaProxy {
  protected AbstractUacaProxy() {}

  protected abstract Client client();

  protected abstract URL url();

  public final void send(final String path, @Nullable final Object request) {
    WebTarget target = null;
    Response response = null;

    try {
      try {
        target = this.buildTarget().path(path);

        this.filterRequest(target, request);

        response = this.sendRequest(target, request);

        this.processResponse(target, request, response);
      } finally {
        if (response != null) {
          response.close();
        }
      }
    } catch (Exception failure) {
      String uri = target != null ? target.getUri().toString() : "unknown";

      this.reportFailure(format("POST %s -> unexpected failure", uri), failure);

      propagate(failure);
    }
  }

  protected WebTarget buildTarget() {
    return this.client().target(this.url().toString());
  }

  @SuppressWarnings("unused")
  protected void filterRequest(final WebTarget target, @Nullable final Object request) {}

  @SuppressWarnings("static-method")
  protected Response sendRequest(final WebTarget target, @Nullable final Object request) {
    return target.request().post(entity(request, APPLICATION_JSON_TYPE));
  }

  @SuppressWarnings("unused")
  protected void processResponse(final WebTarget target, @Nullable final Object request, final Response response) {}

  @SuppressWarnings("unused")
  protected void reportFailure(final String message, @Nullable final Exception failure) {}
}
