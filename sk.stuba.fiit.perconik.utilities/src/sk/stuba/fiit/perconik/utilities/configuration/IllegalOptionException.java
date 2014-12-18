package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class IllegalOptionException extends IllegalArgumentException {
  private static final long serialVersionUID = 0;

  /**
   * Creates a new instance with no detail message.
   */
  public IllegalOptionException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public IllegalOptionException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public IllegalOptionException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public IllegalOptionException(@Nullable final Throwable cause) {
    super(cause);
  }
}
