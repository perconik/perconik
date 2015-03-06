package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

final class RegularOptionAccessor<T> extends AbstractOptionAccessor<T> {
  private final OptionParser<T> parser;

  RegularOptionAccessor(final OptionParser<T> parser, final String key, @Nullable final T defaultValue) {
    super(parser.type(), key, defaultValue);

    this.parser = parser;
  }

  @Override
  protected OptionParser<? extends T> parser() {
    return this.parser;
  }
}
