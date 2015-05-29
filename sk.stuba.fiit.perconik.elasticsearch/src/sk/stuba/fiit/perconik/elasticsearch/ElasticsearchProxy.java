package sk.stuba.fiit.perconik.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public interface ElasticsearchProxy extends AutoCloseable {
  public interface Task<T> {
    public T perform(TransportClient client);
  }

  public <T> T execute(Task<T> task);

  public Iterable<InetSocketTransportAddress> addresses();

  public Settings settings();
}
