package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when resource registration fails.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ResourceRegistrationException extends IllegalStateException {
  private static final long serialVersionUID = 0;

  /**
   * Creates a new instance with no detail message.
   */
  public ResourceRegistrationException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public ResourceRegistrationException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ResourceRegistrationException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ResourceRegistrationException(@Nullable final Throwable cause) {
    super(cause);
  }
}
