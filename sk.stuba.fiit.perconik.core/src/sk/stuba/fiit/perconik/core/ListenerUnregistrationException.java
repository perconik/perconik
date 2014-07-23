package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when listener unregistration fails.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class ListenerUnregistrationException extends IllegalStateException {
  private static final long serialVersionUID = 0;

  /**
   * Creates a new instance with no detail message.
   */
  public ListenerUnregistrationException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public ListenerUnregistrationException(@Nullable String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ListenerUnregistrationException(@Nullable String message, @Nullable Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ListenerUnregistrationException(@Nullable Throwable cause) {
    super(cause);
  }
}
