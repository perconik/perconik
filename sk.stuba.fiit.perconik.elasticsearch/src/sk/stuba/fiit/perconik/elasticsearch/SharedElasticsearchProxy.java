package sk.stuba.fiit.perconik.elasticsearch;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;

import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.Integer.toHexString;
import static java.lang.String.format;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableMap.copyOf;
import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.util.concurrent.MoreExecutors.shutdownAndAwaitTermination;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;

import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.clientTransportAddresses;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toDefaultString;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;
import static sk.stuba.fiit.perconik.utilities.configuration.MapOptions.from;

public class SharedElasticsearchProxy extends AbstractElasticsearchProxy {
  private static final TimeValue waitBeforeClientClose = of(4, SECONDS);

  protected final ElasticsearchOptions options;

  private final ElasticsearchReporter reporter;

  private SharedSecrets secrets;

  private ImmutableList<TransportAddress> addresses;

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

    private static TransportClient open(final ElasticsearchReporter reporter, final Settings settings, final Iterable<TransportAddress> addresses) {
      assert settings != null;

      reporter.logNotice(format("opening shared client for %s", identify(settings)));

      try {
        TransportClient client = new TransportClient(settings);

        client.addTransportAddresses(toArray(addresses, TransportAddress.class));

        reporter.logNotice(format("shared client for %s opened -> %s", identify(settings), toDefaultString(client)));

        return client;
      } catch (Exception failure) {
        reporter.logError(format("unable to open shared client for %s", identify(settings)), failure);

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

    synchronized TransportClient client(final ElasticsearchReporter reporter, final Settings settings, final Iterable<TransportAddress> addresses) {
      assert settings != null;

      TransportClient client = this.clients.get(settings);

      if (client == null) {
        client = open(reporter, settings, addresses);

        this.clients.put(settings, client);
      }

      return client;
    }
  }

  private static ElasticsearchOptions safeOptions(final Options options) {
    return ElasticsearchOptions.View.of(from(copyOf(options.toMap())));
  }

  private static ImmutableList<TransportAddress> readAddresses(final Options options) {
    ImmutableList.Builder<TransportAddress> builder = ImmutableList.builder();

    for (InetSocketAddress address: clientTransportAddresses.getValue(options)) {
      builder.add(new InetSocketTransportAddress(address));
    }

    return builder.build();
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
    ElasticsearchOptions options = safeOptions(this.options);

    Settings update = normalizeSettings(options.toSettings());

    if (!update.equals(this.settings)) {
      if (this.settings != null) {
        this.secrets.release(this.reporter, this.settings, waitBeforeClientClose);
      }

      this.settings = update;
      this.addresses = readAddresses(options);
      this.secrets = SharedSecrets.obtain(this.reporter, update);
    }
  }

  public final ImmutableList<TransportAddress> addresses() {
    synchronized (this) {
      if (this.addresses == null) {
        this.reload();
      }

      return this.addresses;
    }
  }

  public final Settings settings() {
    synchronized (this) {
      if (this.settings == null) {
        this.reload();
      }

      return this.settings;
    }
  }

  public final Settings update() {
    synchronized (this) {
      Settings settings = this.settings;

      this.reload();

      return settings;
    }
  }

  @Override
  protected final TransportClient client() {
    synchronized (this) {
      if (this.settings == null || this.addresses == null) {
        this.reload();
      }

      return this.secrets.client(this.reporter, this.settings, this.addresses);
    }
  }

  @Override
  protected void reportMessage(final String message) {
    this.reporter.logNotice(message);
  }

  @Override
  protected void reportFailure(final String message, final Exception failure) {
    this.reporter.logError(message, failure);
    this.reporter.displayError(message, failure);
  }

  public final void close() {
    synchronized (this) {
      if (this.settings == null) {
        this.reload();
      }

      this.secrets.release(this.reporter, this.settings, waitBeforeClientClose);

      this.closeHook();
    }
  }

  protected void closeHook() {}
}
