package sk.stuba.fiit.perconik.elasticsearch;

import javax.annotation.Nullable;

import org.elasticsearch.client.transport.TransportClient;

import static java.lang.String.format;

public abstract class AbstractElasticsearchProxy implements ElasticsearchProxy {
  public AbstractElasticsearchProxy() {}

  protected abstract TransportClient client();

  public final <T> T execute(final Task<T> task) {
    final TransportClient client = this.client();

    try {
      return task.perform(client);
    } catch (Exception failure) {
      this.reportFailure(format("unexpected failure while performing %s on %s", task, client), failure);

      throw failure;
    }
  }

  @SuppressWarnings("unused")
  protected void reportMessage(final String message) {}

  @SuppressWarnings("unused")
  protected void reportFailure(final String message, @Nullable final Exception failure) {}
}
