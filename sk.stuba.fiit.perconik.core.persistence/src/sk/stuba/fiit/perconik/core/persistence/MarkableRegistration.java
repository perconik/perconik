package sk.stuba.fiit.perconik.core.persistence;

/**
 * A {@code Registration} with markable registration status. This interface is
 * an extension to the {@code Registration} interface able to mark itself with
 * desired registration status aside from the current registration status.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface MarkableRegistration extends Registration {
  /**
   * Returns {@code true} if the underlying registrable
   * object is marked as registered, {@code false} otherwise.
   */
  public boolean hasRegistredMark();
}
