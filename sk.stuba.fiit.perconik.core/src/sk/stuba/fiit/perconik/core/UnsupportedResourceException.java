package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown to indicate that the requested
 * operation on a resource is not supported.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class UnsupportedResourceException extends UnsupportedOperationException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public UnsupportedResourceException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public UnsupportedResourceException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public UnsupportedResourceException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public UnsupportedResourceException(@Nullable final Throwable cause) {
    super(cause);
  }
}
