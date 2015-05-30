package sk.stuba.fiit.perconik.elasticsearch.ui.preferences;

import java.util.Map;
import java.util.SortedMap;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSortedMap;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import sk.stuba.fiit.perconik.elasticsearch.ElasticsearchProxy;
import sk.stuba.fiit.perconik.elasticsearch.ElasticsearchProxy.Task;
import sk.stuba.fiit.perconik.elasticsearch.SharedElasticsearchProxy;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;

import static java.lang.System.lineSeparator;

import static com.google.common.collect.Maps.newLinkedHashMap;

import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.displayErrors;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.logErrors;
import static sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions.Schema.logNotices;

final class ElasticsearchHandler {
  static ElasticsearchProxy createProxy(final ElasticsearchOptions options) {
    return new SharedElasticsearchProxy(stripReporting(options));
  }

  static ElasticsearchOptions stripReporting(final ElasticsearchOptions options) {
    Map<String, Object> raw = newLinkedHashMap(options.toMap());

    raw.put(displayErrors.getKey(), false);

    raw.put(logNotices.getKey(), false);
    raw.put(logErrors.getKey(), false);

    return ElasticsearchOptions.View.of(MapOptions.view(raw));
  }

  static String formatSettings(final Settings settings) {
    return Joiner.on(lineSeparator()).withKeyValueSeparator(" = ").join(sortSettings(settings));
  }

  static SortedMap<String, ?> sortSettings(final Settings settings) {
    return ImmutableSortedMap.copyOf(settings.getAsMap());
  }

  static ClusterStateResponse requestClusterState(final ElasticsearchProxy proxy) {
    return proxy.execute(new Task<ClusterStateResponse>() {
      public ClusterStateResponse perform(final TransportClient client) {
        return client.admin().cluster().prepareState().get();
      }
    });
  }

  static ClusterStatsResponse requestClusterStats(final ElasticsearchProxy proxy) {
    return proxy.execute(new Task<ClusterStatsResponse>() {
      public ClusterStatsResponse perform(final TransportClient client) {
        return client.admin().cluster().prepareClusterStats().get();
      }
    });
  }
}
