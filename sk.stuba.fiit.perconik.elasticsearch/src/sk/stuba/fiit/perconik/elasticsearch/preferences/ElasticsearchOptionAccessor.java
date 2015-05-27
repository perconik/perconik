package sk.stuba.fiit.perconik.elasticsearch.preferences;

import javax.annotation.Nullable;

import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.eclipse.jface.preference.PreferenceStoreOptions;
import sk.stuba.fiit.perconik.utilities.configuration.AbstractOptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.OptionParser;
import sk.stuba.fiit.perconik.utilities.configuration.Options;
import sk.stuba.fiit.perconik.utilities.configuration.OptionsReader;
import sk.stuba.fiit.perconik.utilities.configuration.OptionsWriter;

import static com.google.common.base.Preconditions.checkArgument;

//TODO rm
final class ElasticsearchOptionAccessor<T> extends AbstractOptionAccessor<T> {
  private final OptionParser<T> parser;

  ElasticsearchOptionAccessor(final TypeToken<T> type, final OptionParser<T> parser, final String key, @Nullable final T defaultValue) {
    super(type, key, defaultValue);

    this.parser = parser;
  }

  @Override
  protected OptionsReader reader(final Options options) {
    checkArgument(options instanceof PreferenceStoreOptions);

    return new ElasticsearchOptionsReader((PreferenceStoreOptions) options);
  }

  @Override
  protected OptionsWriter writer(final Options options) {
    checkArgument(options instanceof PreferenceStoreOptions);

    return new ElasticsearchOptionsWriter((PreferenceStoreOptions) options);
  }

  @Override
  protected OptionParser<? extends T> parser() {
    return this.parser;
  }
}
