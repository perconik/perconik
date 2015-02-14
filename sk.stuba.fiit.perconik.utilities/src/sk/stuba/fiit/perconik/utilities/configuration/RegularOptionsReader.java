package sk.stuba.fiit.perconik.utilities.configuration;

import static java.util.Objects.requireNonNull;

final class RegularOptionsReader extends AbstractOptionsReader {
  private final Options options;

  RegularOptionsReader(final Options options) {
    this.options = requireNonNull(options);
  }

  @Override
  protected Options options() {
    return this.options;
  }
}
