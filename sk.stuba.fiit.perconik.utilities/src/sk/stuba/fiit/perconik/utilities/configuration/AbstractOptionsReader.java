package sk.stuba.fiit.perconik.utilities.configuration;

public abstract class AbstractOptionsReader implements OptionsReader {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractOptionsReader() {}

  protected abstract Options options();

  public <T> T get(final OptionParser<? extends T> parser, final String key) {
    Object raw = this.getRaw(key);

    if (raw == null) {
      return null;
    }

    try {
      return parser.parse(raw);
    } catch (RuntimeException failure) {
      // silently ignore and return null so default value
      // can be easily supplied by an option accessor

      return null;
    }
  }

  public Object getRaw(final String key) {
    return this.options().get(key);
  }
}
