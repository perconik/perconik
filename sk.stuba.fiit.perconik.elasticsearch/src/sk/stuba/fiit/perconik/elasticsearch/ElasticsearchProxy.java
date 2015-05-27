package sk.stuba.fiit.perconik.elasticsearch;

import com.google.common.base.Optional;

import org.elasticsearch.client.transport.TransportClient;

public interface ElasticsearchProxy extends AutoCloseable {
  public interface Task<T> {
    public T perform(TransportClient client);
  }

  public <T> Optional<T> execute(Task<T> task);
}
