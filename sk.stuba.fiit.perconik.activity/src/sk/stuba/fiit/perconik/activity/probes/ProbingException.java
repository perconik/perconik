package sk.stuba.fiit.perconik.activity.probes;

import javax.annotation.Nullable;

public class ProbingException extends RuntimeException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  protected ProbingException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  protected ProbingException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public ProbingException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public ProbingException(@Nullable final Throwable cause) {
    super(cause);
  }
}
