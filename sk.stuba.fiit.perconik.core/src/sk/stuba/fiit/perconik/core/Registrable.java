package sk.stuba.fiit.perconik.core;

/**
 * A mixin interface for an object that can be registered and unregistered.
 * Registrable objects are usually managed by a registration manager which
 * controls the registration process and calls the hook methods provided by
 * this interface at appropriate time.
 *
 * <p>All hook methods may throw a {@code RuntimeException} and it is up to
 * the registration manager to handle the exception or propagate it further.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Registrable {
  /**
   * Pre-register block. Called by the registration manager
   * right before it internally registers this object.
   *
   * @throws RuntimeException if pre-registration fails
   */
  public void preRegister();

  /**
   * Post-register block. Called by the registration manager
   * right after it internally registers this object.
   *
   * @throws RuntimeException if post-registration fails
   */
  public void postRegister();

  /**
   * Pre-unregister block. Called by the registration manager
   * right before it internally unregisters this object.
   *
   * @throws RuntimeException if pre-unregistration fails
   */
  public void preUnregister();

  /**
   * Post-unregister block. Called by the registration manager
   * after before it internally unregisters this object.
   *
   * @throws RuntimeException if post-unregistration fails
   */
  public void postUnregister();
}
