package sk.stuba.fiit.perconik.utilities.configuration;

import static java.util.Objects.requireNonNull;

public final class RegularOptionsReader extends AbstractOptionsReader {
  private final Options options;

  private final OptionParser parser;

  private RegularOptionsReader(final Options options, final OptionParser parser) {
    this.options = requireNonNull(options);
    this.parser = requireNonNull(parser);
  }

  public static RegularOptionsReader create(final OptionParser parser, final Options options) {
    return new RegularOptionsReader(options, parser);
  }

  public static RegularOptionsReader of(final Options options) {
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
