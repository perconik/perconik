package sk.stuba.fiit.perconik.core.persistence;

/**
 * A registration status marker for {@code MarkableRegistration} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface RegistrationMarker<R extends MarkableRegistration> {
  /**
   * Applies the registered mark by registering or
   * unregistering the underlying registrable object.
   *
   * <p><b>Note:</b> This method propagates runtime exceptions thrown
   * by the registration mechanism of the underlying registrable object.
   * @return registration with updated current registration status
   */
  public R applyRegisteredMark();

  /**
   * Updates the registered mark according to the current
   * registration status of the underlying registrable object.
   * @return registration with updated registered mark
   */
  public R updateRegisteredMark();

  /**
   * Marks registration with the desired registration status.
   * In other words sets the registered mark to the given value.
   * @param status the desired registration status
   * @return registration with updated registered mark
   */
  public R markRegistered(boolean status);
}
