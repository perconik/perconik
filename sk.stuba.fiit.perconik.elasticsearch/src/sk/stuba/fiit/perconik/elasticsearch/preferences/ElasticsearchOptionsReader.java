package sk.stuba.fiit.perconik.elasticsearch.preferences;

import sk.stuba.fiit.perconik.eclipse.jface.preference.AbstractPreferenceStoreOptionsReader;
import sk.stuba.fiit.perconik.eclipse.jface.preference.PreferenceStoreOptions;

import static com.google.common.base.Preconditions.checkNotNull;

final class ElasticsearchOptionsReader extends AbstractPreferenceStoreOptionsReader {
  private final PreferenceStoreOptions options;

  ElasticsearchOptionsReader(final PreferenceStoreOptions options) {
    this.options = checkNotNull(options);
  }

  @Override
  protected PreferenceStoreOptions options() {
    return this.options;
  }

  @Override
  protected Object fromStringToRaw(final String value) {
    return value;
  }
}
