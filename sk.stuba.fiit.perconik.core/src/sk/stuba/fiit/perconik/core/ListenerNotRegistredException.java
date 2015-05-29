package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when a provided listener is not registered but requested.
 *
 * <p>This exception may be also thrown when an attempt is made to unregister
 * a listener that is not registered and the core listener service decided
 * to inform about that.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class ListenerNotRegistredException extends IllegalStateException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public ListenerNotRegistredException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public ListenerNotRegistredException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ListenerNotRegistredException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ListenerNotRegistredException(@Nullable final Throwable cause) {
    super(cause);
  }
}
