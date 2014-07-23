package sk.stuba.fiit.perconik.core.persistence;

import java.io.InvalidObjectException;

import javax.annotation.Nullable;

/**
 * Indicates that one or more deserialized listeners failed validation
 * tests. The argument should provide the reason for the failure.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class InvalidListenerException extends InvalidObjectException {
  private static final long serialVersionUID = 0;

  /**
   * Creates a new instance with no detail message.
   */
  public InvalidListenerException() {
    super(null);

    this.fillInStackTrace();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public InvalidListenerException(@Nullable String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public InvalidListenerException(@Nullable String message, @Nullable Throwable cause) {
    super(message);

    this.initCause(cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public InvalidListenerException(@Nullable Throwable cause) {
    this(null, cause);
  }
}
