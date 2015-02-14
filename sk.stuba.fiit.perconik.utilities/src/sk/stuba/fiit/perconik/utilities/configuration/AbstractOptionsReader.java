package sk.stuba.fiit.perconik.utilities.configuration;

public abstract class AbstractOptionsReader implements OptionsReader {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractOptionsReader() {}

  protected abstract Options options();

  public <T> T get(final OptionParser<? extends T> parser, final String key) {
    Object value = this.getRaw(key);

    return value != null ? parser.parse(value) : null;
  }

  public Object getRaw(final String key) {
    return this.options().get(key);
  }
}
