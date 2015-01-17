package sk.stuba.fiit.perconik.utilities.configuration;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ScopedConfigurable extends Configurable {
  /**
   * Gets scoped options of this configurable instance.
   *
   * <p>Note that the implementation specifies whether
   * this method returns a snapshot or view of options.
   *
   * @param scope the scope to be applied, not {@code null}
   *
   * @return either a snapshot or view of options
   */
  public Options getOptions(Scope scope);
}
