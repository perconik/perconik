package sk.stuba.fiit.perconik.core.persistence;

/**
 * Current registration status of a {@code Registrable} instance.
 * The current registration status is obtained from an underlying
 * registrable object.
 * 
 * @see sk.stuba.fiit.perconik.core.Registrable Registrable
 * @see sk.stuba.fiit.perconik.core.services.Provider Provider
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Registration {
  /**
   * Returns {@code true} if the underlying registrable
   * object is currently registered, {@code false} otherwise.
   */
  public boolean isRegistered();

  /**
   * Returns {@code true} if the underlying registrable
   * object is provided by an object provider, {@code false} otherwise.
   * @throws UnsupportedOperationException if the {@code isProvided}
   *         operation is not supported by this registration
   */
  public boolean isProvided();
}
