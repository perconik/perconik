package sk.stuba.fiit.perconik.elasticsearch;

import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;

import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.lang.Integer.toHexString;
import static java.lang.String.format;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.util.concurrent.MoreExecutors.shutdownAndAwaitTermination;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.toDefaultString;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

public class SharedElasticsearchProxy extends AbstractElasticsearchProxy {
  private static final TimeValue waitBeforeClientClose = of(8, SECONDS);

  protected final ElasticsearchOptions options;

  private final ElasticsearchReporter reporter;

  private SharedSecrets secrets;

  private Settings settings;

  public SharedElasticsearchProxy(final ElasticsearchOptions options) {
    this.options = checkNotNull(options);
    this.reporter = new ElasticsearchReporter(options);

    this.reload();
  }

  private static final class SharedSecrets {
    private static final SharedSecrets instance = new SharedSecrets();

    private final Multiset<Settings> counters;

    private final Map<Settings, TransportClient> clients;

    private SharedSecrets() {
      this.counters = HashMultiset.create();
      this.clients = newHashMap();
    }

    static SharedSecrets obtain(final ElasticsearchReporter reporter, final Settings settings) {
      synchronized (instance) {
        return instance.connect(reporter, settings);
      }
    }

    static String identify(final Settings settings) {
      return toHexString(settings.hashCode());
    }

    private SharedSecrets connect(final ElasticsearchReporter reporter, final Settings settings) {
      assert settings != null;

      int count = this.counters.add(settings, 1) + 1;

      assert count > 0;

      reporter.logNotice(format("connect to %s -> %d connections", identify(settings), count));

      return this;
    }

    private SharedSecrets disconnect(final ElasticsearchReporter reporter, final Settings settings, final TimeValue wait) {
      assert settings != null;

      int count = this.counters.remove(settings, 1) - 1;

      assert count >= 0;

      reporter.logNotice(format("disconnect from %s -> %d connections", identify(settings), count));

      if (count == 0) {
        TransportClient client = this.clients.remove(settings);

        if (client != null) {
          close(reporter, settings, client, wait);
        }
      }

      return this;
    }

    private static TransportClient open(final ElasticsearchReporter reporter, final Settings settings) {
      assert settings != null;

      reporter.logNotice(format("opening shared client for %s", identify(settings)));

      try {
        TransportClient client = new TransportClient(settings);

        reporter.logNotice(format("shared client for %s opened -> %s", identify(settings), toDefaultString(client)));

        return client;
      } catch (Exception failure) {
        reporter.logError(format("unable to open shared client for", identify(settings)), failure);

        throw failure;
      }
    }

    private static void close(final ElasticsearchReporter reporter, final Settings settings, final TransportClient client, final TimeValue wait) {
      assert client != null;

      checkNotNull(wait);

      reporter.logNotice(format("closing shared client for %s -> %s", identify(settings), toDefaultString(client)));

      ExecutorService service = newSingleThreadExecutor();

      service.execute(new Runnable() {
        public void run() {
          sleepUninterruptibly(wait.duration(), wait.unit());

          try {
            client.close();

            reporter.logNotice(format("shared client for %s closed", identify(settings)));
          } catch (Exception failure) {
            reporter.logError(format("unable to close shared client for %s -> %s", identify(settings), toDefaultString(client)), failure);
          }
        }
      });

      shutdownAndAwaitTermination(service, 4 * wait.duration(), wait.unit());
    }

    synchronized void release(final ElasticsearchReporter reporter, final Settings settings, final TimeValue wait) {
      assert settings != null;

      this.disconnect(reporter, settings, wait);
    }

    synchronized TransportClient client(final ElasticsearchReporter reporter, final Settings settings) {
      assert settings != null;

      TransportClient client = this.clients.get(settings);

      if (client == null) {
        client = open(reporter, settings);

        this.clients.put(settings, client);
      }

      return client;
    }
  }

  private static Settings normalizeSettings(final Settings settings) {
    Builder builder = settingsBuilder();

    builder.put(settings);

    // automatically overridden
    builder.put("client.type", "transport");
    builder.put("node.client", true);
    builder.put("network.server", false);

    // ensure behavior
    builder.put("node.master", false);
    builder.put("node.data", false);

    // disable HTTP
    builder.put("http.enabled", false);

    return builder.build();
  }

  private void reload() {
    if (this.settings != null) {
      this.secrets.release(this.reporter, this.settings, waitBeforeClientClose);
    }

    this.settings = normalizeSettings(this.options.toSettings());
    this.secrets = SharedSecrets.obtain(this.reporter, this.settings);
  }

  @Override
  public Settings settings() {
    synchronized (this) {
      if (this.settings == null) {
        this.reload();
      }

      return this.settings;
    }
  }

  public Settings update() {
    synchronized (this) {
      this.reload();

      return this.settings;
    }
  }

  @Override
  protected TransportClient client() {
    synchronized (this) {
      return this.secrets.client(this.reporter, this.settings());
    }
  }

  @Override
  protected void reportFailure(final String message, final Exception failure) {
    this.reporter.logError(message, failure);
    this.reporter.displayError(message, failure);
  }

  public void close() {
    synchronized (this) {
      this.secrets.release(this.reporter, this.settings(), waitBeforeClientClose);
    }
  }
}
