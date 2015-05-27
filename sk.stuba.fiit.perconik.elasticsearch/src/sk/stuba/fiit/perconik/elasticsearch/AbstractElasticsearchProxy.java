package sk.stuba.fiit.perconik.elasticsearch;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import static java.lang.String.format;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;

public abstract class AbstractElasticsearchProxy implements AutoCloseable {
  public AbstractElasticsearchProxy() {}

  protected abstract Settings settings();

  protected abstract TransportClient client();

  public interface Task<T> {
    public T perform(TransportClient client);
  }

  public final <T> Optional<T> execute(final Task<T> task) {
    final TransportClient client = this.client();

    try {
      return of(task.perform(client));
    } catch (Exception failure) {
      this.reportFailure(format("unexpected failure while performing %s on %s", task, client), failure);
    }

    return absent();
  }

  @SuppressWarnings("unused")
  protected void reportFailure(final String message, @Nullable final Exception failure) {}
}
