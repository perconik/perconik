package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown by an implementation of core services when input parameters
 * are invalid. This may occur either because the class is not an instance
 * of {@link sk.stuba.fiit.perconik.core.Resource Resource} or it does not
 * meet any other service implementation specific requirements.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class IllegalResourceClassException extends IllegalArgumentException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public IllegalResourceClassException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public IllegalResourceClassException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public IllegalResourceClassException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public IllegalResourceClassException(@Nullable final Throwable cause) {
    super(cause);
  }
}
