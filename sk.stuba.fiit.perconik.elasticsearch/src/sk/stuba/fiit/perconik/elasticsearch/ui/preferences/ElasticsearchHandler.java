package sk.stuba.fiit.perconik.elasticsearch.ui.preferences;

import java.util.SortedMap;
import java.util.concurrent.ExecutionException;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSortedMap;

import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import sk.stuba.fiit.perconik.elasticsearch.ElasticsearchProxy;
import sk.stuba.fiit.perconik.elasticsearch.ElasticsearchProxy.Task;
import sk.stuba.fiit.perconik.elasticsearch.SharedElasticsearchProxy;
import sk.stuba.fiit.perconik.elasticsearch.preferences.ElasticsearchOptions;

import static com.google.common.base.Throwables.propagate;

final class ElasticsearchHandler {
  static ElasticsearchProxy createProxy(final ElasticsearchOptions options) {
    return new SharedElasticsearchProxy(options);
  }

  static String formatSettings(final Settings settings) {
    return Joiner.on('\n').withKeyValueSeparator(" = ").join(sortSettings(settings));
  }

  static SortedMap<String, ?> sortSettings(final Settings settings) {
    return ImmutableSortedMap.copyOf(settings.getAsMap());
  }

  static ClusterStatsResponse requestClusterStats(final ElasticsearchProxy proxy) {
    return proxy.execute(new Task<ClusterStatsResponse>() {
      public ClusterStatsResponse perform(final TransportClient client) {
        try {
          return client.admin().cluster().prepareClusterStats().execute().get();
        } catch (InterruptedException | ExecutionException failure) {
          throw propagate(failure);
        }
      }
    });
  }
}
