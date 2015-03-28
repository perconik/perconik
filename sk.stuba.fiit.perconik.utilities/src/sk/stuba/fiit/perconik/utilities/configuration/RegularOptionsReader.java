package sk.stuba.fiit.perconik.utilities.configuration;

import static com.google.common.base.Preconditions.checkNotNull;

final class RegularOptionsReader extends AbstractOptionsReader {
  private final Options options;

  RegularOptionsReader(final Options options) {
    this.options = checkNotNull(options);
  }

  @Override
  protected Options options() {
    return this.options;
  }
}
