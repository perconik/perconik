package sk.stuba.fiit.perconik.elasticsearch.preferences;

import java.util.Map;

import com.google.common.base.Joiner;

import sk.stuba.fiit.perconik.eclipse.jface.preference.AbstractPreferenceStoreOptionsWriter;
import sk.stuba.fiit.perconik.eclipse.jface.preference.PreferenceStoreOptions;

import static com.google.common.base.Preconditions.checkNotNull;

final class ElasticsearchOptionsWriter extends AbstractPreferenceStoreOptionsWriter {
  private final PreferenceStoreOptions options;

  ElasticsearchOptionsWriter(final PreferenceStoreOptions options) {
    this.options = checkNotNull(options);
  }

  @Override
  protected PreferenceStoreOptions options() {
    return this.options;
  }

  @Override
  protected String fromRawToString(final Object value) {
    if (value instanceof Iterable) {
      return Joiner.on(",").join((Iterable<?>) value);
    } else if (value instanceof Map) {
      return Joiner.on(",").withKeyValueSeparator("=").join((Map<?, ?>) value);
    }

    return value.toString();
  }
}
