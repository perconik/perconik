package sk.stuba.fiit.perconik.utilities.configuration;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Configurable {
  /**
   * Gets options of this configurable instance.
   *
   * <p>Note that the implementation specifies whether
   * this method returns a snapshot or view of options.
   *
   * @return either a snapshot or view of options
   */
  public Options getOptions();
}
