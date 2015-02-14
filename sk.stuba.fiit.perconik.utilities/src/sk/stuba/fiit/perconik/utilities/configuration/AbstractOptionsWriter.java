package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

public abstract class AbstractOptionsWriter implements OptionsWriter {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractOptionsWriter() {}

  protected abstract Options options();

  public <T> Object put(final OptionParser<? extends T> parser, final String key, @Nullable final Object value) {
    return this.putRaw(key, value != null ? parser.parse(value) : null);
  }

  public Object putRaw(final String key, @Nullable final Object value) {
    return this.options().put(key, value);
  }
}
