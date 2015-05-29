package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when an attempt is made to register a resource that is already
 * registered and the core resource service decided to inform about that.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ResourceAlreadyRegistredException extends IllegalStateException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public ResourceAlreadyRegistredException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public ResourceAlreadyRegistredException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ResourceAlreadyRegistredException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ResourceAlreadyRegistredException(@Nullable final Throwable cause) {
    super(cause);
  }
}
