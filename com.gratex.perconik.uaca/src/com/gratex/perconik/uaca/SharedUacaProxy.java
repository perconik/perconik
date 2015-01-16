package com.gratex.perconik.uaca;

import java.net.URL;
import java.util.concurrent.ExecutorService;

import javax.annotation.Nullable;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import com.gratex.perconik.uaca.preferences.UacaOptions;
import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR;
import static javax.ws.rs.core.Response.Status.Family.SERVER_ERROR;

import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.applicationUrl;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public class SharedUacaProxy extends AbstractUacaProxy {
  private static final ExecutorService sharedExecutor = PlatformExecutors.newLimitedThreadPool();

  protected final Options options;

  public SharedUacaProxy() {
    this(UacaPreferences.getShared());
  }

  public SharedUacaProxy(final Options options) {
    this.options = requireNonNull(options);
  }

  public static final void checkConnection(final String url) {
    newClient().target(url).path("ide/checkin").request().options().close();
  }

  public static final void checkConnection(final URL url) {
    checkConnection(url.toString());
  }

  @Override
  protected final ExecutorService executor() {
    return sharedExecutor;
  }

  @Override
  protected URL url() {
    if (this.options instanceof UacaOptions) {
      return ((UacaOptions) this.options).getApplicationUrl();
    }

    return newUrl(this.options.toMap().get(applicationUrl).toString());
  }

  @Override
  protected void filterRequest(final WebTarget target, @Nullable final Object request) {
    UacaReporter.logRequest(target, request);
  }

  @Override
  protected void processResponse(final WebTarget target, @Nullable final Object request, final Response response) {
    StatusType status = response.getStatusInfo();
    Family family = status.getFamily();

    if (family == CLIENT_ERROR || family == SERVER_ERROR) {
      String message = format("UacaProxy: POST %s -> %s %d %s", target.getUri(), family, status.getStatusCode(), status.getReasonPhrase());

      throw new IllegalStateException(message);
    }
  }

  @Override
  protected void reportFailure(final String message, final Exception failure) {
    UacaReporter.logError(message, failure);
    UacaReporter.displayError(message, failure);
  }
}
