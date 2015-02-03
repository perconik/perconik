package sk.stuba.fiit.perconik.utilities.configuration;

import static java.util.Objects.requireNonNull;

public final class RegularOptionsWriter extends AbstractOptionsWriter {
  private final Options options;

  private final OptionParser parser;

  private RegularOptionsWriter(final Options options, final OptionParser parser) {
    this.options = requireNonNull(options);
    this.parser = requireNonNull(parser);
  }

  public static RegularOptionsWriter create(final OptionParser parser, final Options options) {
    return new RegularOptionsWriter(options, parser);
  }

  public static RegularOptionsWriter of(final Options options) {
    return create(RegularOptionParser.getInstance(), options);
  }

  @Override
  protected Options options() {
    return this.options;
  }

  @Override
  protected OptionParser parser() {
    return this.parser;
  }
}
