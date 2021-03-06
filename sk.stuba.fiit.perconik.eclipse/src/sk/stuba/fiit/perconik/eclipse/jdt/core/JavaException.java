package sk.stuba.fiit.perconik.eclipse.jdt.core;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.eclipse.core.runtime.RuntimeCoreException;

public final class JavaException extends RuntimeCoreException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public JavaException() {
    super();
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public JavaException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public JavaException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public JavaException(@Nullable final Throwable cause) {
    super(cause);
  }
}
