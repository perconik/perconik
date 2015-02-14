package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

import static java.util.Objects.requireNonNull;

final class RegularOptionAccessor<T> extends AbstractOptionAccessor<T> {
  private final OptionParser<? extends T> parser;

  RegularOptionAccessor(final OptionParser<? extends T> parser, final String key, @Nullable final T defaultValue) {
    super(key, defaultValue);

    this.parser = requireNonNull(parser);
  }

  @Override
  protected OptionParser<? extends T> parser() {
    return this.parser;
  }
}
