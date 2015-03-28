package sk.stuba.fiit.perconik.utilities.configuration;

import static com.google.common.base.Preconditions.checkNotNull;

final class RegularOptionsWriter extends AbstractOptionsWriter {
  private final Options options;

  RegularOptionsWriter(final Options options) {
    this.options = checkNotNull(options);
  }

  @Override
  protected Options options() {
    return this.options;
  }
}
