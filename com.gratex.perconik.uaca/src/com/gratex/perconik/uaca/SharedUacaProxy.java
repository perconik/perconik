package com.gratex.perconik.uaca;

import java.net.URL;
import java.util.concurrent.ExecutorService;

import javax.annotation.Nullable;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

import com.gratex.perconik.uaca.preferences.UacaOptions;
import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.data.providers.MapperProvider;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.lang.String.format;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR;
import static javax.ws.rs.core.Response.Status.Family.SERVER_ERROR;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.MoreExecutors.shutdownAndAwaitTermination;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.toDefaultString;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

public class SharedUacaProxy extends AbstractUacaProxy {
  private static final String connectionCheckPath = "ide/checkin";

  private static final TimeValue waitBeforeClientClose = of(8, SECONDS);

  protected final UacaOptions options;

  private final UacaReporter reporter;

  private final SharedSecrets secrets;

  public SharedUacaProxy() {
    this(UacaPreferences.getShared());
  }

  public SharedUacaProxy(final UacaOptions options) {
    this.options = checkNotNull(options);
    this.reporter = new UacaReporter(options);
    this.secrets = SharedSecrets.obtain(this.reporter);
  }

  public static final void checkConnection(final String url) {
    newClient().target(url).path(connectionCheckPath).request().options().close();
  }

  public static final void checkConnection(final URL url) {
    checkConnection(url.toString());
  }

  private static final class SharedSecrets {
    private static final SharedSecrets instance = new SharedSecrets();

    private long counter = 0L;

    private Client client;

    private SharedSecrets() {}

    static SharedSecrets obtain(final UacaReporter reporter) {
      synchronized (instance) {
        return instance.connect(reporter);
      }
    }

    private SharedSecrets connect(final UacaReporter reporter) {
      long count = ++ this.counter;

      assert count > 0;

      reporter.logNotice(format("connect -> %d connections", count));

      return this;
    }

    private SharedSecrets disconnect(final UacaReporter reporter, final TimeValue wait) {
      long count = -- this.counter;

      assert count >= 0;

      reporter.logNotice(format("disconnect -> %d connections", count));

      if (count == 0L && this.client != null) {
        close(reporter, this.client, wait);
      }

      return this;
    }

    private static Client open(final UacaReporter reporter) {
      reporter.logNotice(format("opening shared client"));

      try {
        Client client = newClient().register(MapperProvider.class);

        reporter.logNotice(format("shared client opened -> %s", toDefaultString(client)));

        return client;
      } catch (Exception failure) {
        reporter.logError("unable to open shared client", failure);

        throw failure;
      }
    }

    private static void close(final UacaReporter reporter, final Client client, final TimeValue wait) {
      assert client != null;

      checkNotNull(wait);

      reporter.logNotice(format("closing shared client -> %s", toDefaultString(client)));

      ExecutorService service = newSingleThreadExecutor();

      service.execute(new Runnable() {
        public void run() {
          sleepUninterruptibly(wait.duration(), wait.unit());

          try {
            client.close();

            reporter.logNotice("shared client closed");
          } catch (Exception failure) {
            reporter.logError(format("unable to close shared client -> %s", toDefaultString(client)), failure);
          }
        }
      });

      shutdownAndAwaitTermination(service, 4 * wait.duration(), wait.unit());
    }

    synchronized void release(final UacaReporter reporter, final TimeValue wait) {
      this.disconnect(reporter, wait);
    }

    synchronized Client client(final UacaReporter reporter) {
      if (this.client == null) {
        this.client = open(reporter);
      }

      return this.client;
    }
  }

  @Override
  protected final Client client() {
    return this.secrets.client(this.reporter);
  }

  @Override
  protected final URL url() {
    return this.options.getApplicationUrl();
  }

  @Override
  protected final void filterRequest(final WebTarget target, @Nullable final Object request) {
    this.reporter.logRequest(target, request);
  }

  @Override
  protected final void processResponse(final WebTarget target, @Nullable final Object request, final Response response) {
    StatusType status = response.getStatusInfo();
    Family family = status.getFamily();

    if (family == CLIENT_ERROR || family == SERVER_ERROR) {
      String message = format("POST %s -> %s %d %s", target.getUri(), family, status.getStatusCode(), status.getReasonPhrase());

      throw new IllegalStateException(message);
    }
  }

  @Override
  protected void reportFailure(final String message, final Exception failure) {
    this.reporter.logError(message, failure);
    this.reporter.displayError(message, failure);
  }

  public final void close() {
    this.secrets.release(this.reporter, waitBeforeClientClose);

    this.closeHook();
  }

  protected void closeHook() {}
}
