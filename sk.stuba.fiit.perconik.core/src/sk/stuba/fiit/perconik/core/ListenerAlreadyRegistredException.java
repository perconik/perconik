package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when an attempt is made to register a listener that is already
 * registered and the core listener service decided to inform about that.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class ListenerAlreadyRegistredException extends IllegalStateException {
  private static final long serialVersionUID = 0;

  /**
   * Creates a new instance with no detail message.
   */
  public ListenerAlreadyRegistredException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public ListenerAlreadyRegistredException(@Nullable String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ListenerAlreadyRegistredException(@Nullable String message, @Nullable Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ListenerAlreadyRegistredException(@Nullable Throwable cause) {
    super(cause);
  }
}
