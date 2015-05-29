package sk.stuba.fiit.perconik.utilities.reflect;

import javax.annotation.Nullable;

/**
 * Unchecked variant of {@link java.lang.ReflectiveOperationException}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ReflectionException extends RuntimeException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public ReflectionException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public ReflectionException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ReflectionException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ReflectionException(@Nullable final Throwable cause) {
    super(cause);
  }
}
