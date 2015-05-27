package sk.stuba.fiit.perconik.elasticsearch.preferences;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

import sk.stuba.fiit.perconik.utilities.configuration.AbstractOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Configurables;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.OptionParser;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.Integer.parseInt;
import static java.util.UUID.randomUUID;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableMap.builder;
import static com.google.common.collect.Lists.newArrayList;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;
import static org.elasticsearch.common.unit.TimeValue.timeValueSeconds;

import static sk.stuba.fiit.perconik.elasticsearch.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptionParsers.byteSizeParser;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptionParsers.timeParser;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toStringOrNull;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.trimLeading;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.option;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.arrayListParser;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.booleanParser;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.pathParser;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.stringParser;
import static sk.stuba.fiit.perconik.utilities.io.MorePaths.path;

public interface ElasticsearchOptions extends Options {
  public static final class Schema {
    static final String qualifier = join(PLUGIN_ID, "preferences");

    public static final OptionAccessor<String> nodeName = option(stringParser(), join(qualifier, "name"), "perconik-client-" + randomUUID());

    public static final OptionAccessor<String> clusterName = option(stringParser(), join(qualifier, "cluster", "name"), "perconik");

    public static final OptionAccessor<ArrayList<InetSocketAddress>> clientTransportAddresses = option(arrayListParser(InetSocketAddressParser.INSTANCE, ",", "", ""), join(qualifier, "client", "transport", "addresses"), newArrayList(new InetSocketAddress("127.0.0.1", 9300)));

    public static final OptionAccessor<Boolean> clientTransportIgnoreClusterName = option(booleanParser(), join(qualifier, "client", "transport", "ignore_cluster_name"), false);

    public static final OptionAccessor<TimeValue> clientTransportPingTimeout = option(timeParser(), join(qualifier, "client", "transport", "ping_timeout"), timeValueSeconds(5));

    public static final OptionAccessor<TimeValue> clientTransportNodesSamplerInterval = option(timeParser(), join(qualifier, "client", "transport", "nodes_sampler_interval"), timeValueSeconds(5));

    public static final OptionAccessor<TimeValue> transportTcpConnectTimeout = option(timeParser(), join(qualifier, "transport", "tcp", "connect_timeout"), timeValueSeconds(30));

    public static final OptionAccessor<Boolean> transportTcpCompress = option(booleanParser(), join(qualifier, "transport", "tcp", "compress"), false);

    public static final OptionAccessor<Boolean> networkTcpNoDelay = option(booleanParser(), join(qualifier, "network", "tcp", "no_delay"), true);

    public static final OptionAccessor<Boolean> networkTcpKeepAlive = option(booleanParser(), join(qualifier, "network", "tcp", "keep_alive"), true);

    public static final OptionAccessor<Boolean> networkTcpReuseAddress = option(booleanParser(), join(qualifier, "network", "tcp", "reuse_address"), false);

    public static final OptionAccessor<ByteSizeValue> networkTcpSendBufferSize = option(byteSizeParser(), join(qualifier, "network", "tcp", "send_buffer_size"), null);

    public static final OptionAccessor<ByteSizeValue> networkTcpReceiveBufferSize = option(byteSizeParser(), join(qualifier, "network", "tcp", "receive_buffer_size"), null);

    public static final OptionAccessor<Path> pathLogs = option(pathParser(), join(qualifier, "path", "logs"), defaultPath("elasticsearch", "logs"));

    public static final OptionAccessor<Path> pathWork = option(pathParser(), join(qualifier, "path", "work"), defaultPath("elasticsearch", "work"));

    public static final OptionAccessor<Boolean> displayErrors = option(booleanParser(), join(qualifier, "display_errors"), true);

    public static final OptionAccessor<Boolean> logNotices = option(booleanParser(), join(qualifier, "log_notices"), false);

    public static final OptionAccessor<Boolean> logErrors = option(booleanParser(), join(qualifier, "log_errors"), true);

    static final ImmutableMap<String, OptionAccessor<?>> accessors;

    static {
      ImmutableMap.Builder<String, OptionAccessor<?>> builder = builder();

      for (OptionAccessor<?> accessor: Configurables.accessors(Schema.class)) {
        builder.put(accessor.getKey(), accessor);
      }

      accessors = builder.build();
    }

    private Schema() {}

    public static ImmutableCollection<OptionAccessor<?>> accessors() {
      return accessors.values();
    }

    static Path defaultPath(final String ... segments) {
      try {
        Location configuration = Platform.getConfigurationLocation();

        return path(configuration.getURL().toURI()).resolve(path("..", segments)).normalize();
      } catch (Exception e) {
        return path(".");
      }
    }

    static enum InetSocketAddressParser implements OptionParser<InetSocketAddress> {
      INSTANCE;

      public InetSocketAddress parse(final Object object) {
        if (object instanceof InetSocketAddress) {
          return (InetSocketAddress) object;
        }

        Iterator<String> parts = Splitter.on(':').limit(2).split(object.toString()).iterator();

        return new InetSocketAddress(parts.next(), parseInt(parts.next()));
      }

      public TypeToken<InetSocketAddress> type() {
        return TypeToken.of(InetSocketAddress.class);
      }

      @Override
      public String toString() {
        return toStringHelper(this).add("type", this.type()).toString();
      }
    }

    // TODO rm
    //    static <T> ElasticsearchOptionAccessor<T> option(final OptionParser<T> parser, final String key, @Nullable final T defaultValue) {
    //      return new ElasticsearchOptionAccessor<>(parser.type(), parser, key, defaultValue);
    //    }

    static Map<String, Object> toMap(final Options options) {
      // TODO rm
      //return Configurables.values(accessors(), new ElasticsearchOptionsReader(options), new LinkedHashMap<String, Object>());

      return Configurables.values(accessors(), options, new LinkedHashMap<String, Object>());
    }

    static Settings toSettings(final Options options) {
      // TODO rm
      //      ImmutableSettings.Builder builder = settingsBuilder();
      //      ElasticsearchOptionsWriter writer = new ElasticsearchOptionsWriter(options);
      //
      //      for (Entry<String, Object> option: toMap(options).entrySet()) {
      //        builder.put(trimLeading(option.getKey(), qualifier), writer.fromRawToString(option.getValue()));
      //      }
      //
      //      return builder.build();

      ImmutableSettings.Builder builder = settingsBuilder();

      for (Entry<String, Object> option: toMap(options).entrySet()) {
        builder.put(trimLeading(option.getKey(), qualifier), toStringOrNull(option.getValue()));
      }

      return builder.build();
    }
  }

  public static final class View extends AbstractOptions implements ElasticsearchOptions, Serializable {
    private static final long serialVersionUID = 0L;

    private final Options options;

    private View(final Options options) {
      this.options = checkNotNull(options);
    }

    public static ElasticsearchOptions of(final Options options) {
      return new View(options);
    }

    public Map<String, Object> toMap() {
      return Schema.toMap(this.options);
    }

    public Settings toSettings() {
      return Schema.toSettings(this.options);
    }
  }

  public Settings toSettings();
}