package sk.stuba.fiit.perconik.core.persistence;

import java.io.InvalidObjectException;

import javax.annotation.Nullable;

/**
 * Indicates that one or more deserialized resources failed validation
 * tests. The argument should provide the reason for the failure.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class InvalidResourceException extends InvalidObjectException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no message.
   */
  public InvalidResourceException() {
    super(null);

    this.fillInStackTrace();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public InvalidResourceException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public InvalidResourceException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message);

    this.initCause(cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public InvalidResourceException(@Nullable final Throwable cause) {
    this(null, cause);
  }
}
