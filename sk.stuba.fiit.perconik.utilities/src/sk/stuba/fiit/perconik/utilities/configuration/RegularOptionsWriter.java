package sk.stuba.fiit.perconik.utilities.configuration;

import static java.util.Objects.requireNonNull;

final class RegularOptionsWriter extends AbstractOptionsWriter {
  private final Options options;

  RegularOptionsWriter(final Options options) {
    this.options = requireNonNull(options);
  }

  @Override
  protected Options options() {
    return this.options;
  }
}
