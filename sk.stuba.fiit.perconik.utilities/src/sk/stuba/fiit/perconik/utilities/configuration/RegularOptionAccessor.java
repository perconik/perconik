package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.newReader;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.newWriter;

final class RegularOptionAccessor<T> extends AbstractOptionAccessor<T> {
  private final OptionParser<T> parser;

  RegularOptionAccessor(final OptionParser<T> parser, final String key, @Nullable final T defaultValue) {
    super(parser.type(), key, defaultValue);

    this.parser = parser;
  }

  @Override
  protected OptionsReader reader(final Options options) {
    return newReader(options);
  }

  @Override
  protected OptionsWriter writer(final Options options) {
    return newWriter(options);
  }

  @Override
  protected OptionParser<? extends T> parser() {
    return this.parser;
  }
}
