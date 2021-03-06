package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when resource unregistration fails.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ResourceUnregistrationException extends IllegalStateException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public ResourceUnregistrationException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public ResourceUnregistrationException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ResourceUnregistrationException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ResourceUnregistrationException(@Nullable final Throwable cause) {
    super(cause);
  }
}
