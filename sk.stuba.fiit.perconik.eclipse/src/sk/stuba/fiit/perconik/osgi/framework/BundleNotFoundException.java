package sk.stuba.fiit.perconik.osgi.framework;

import javax.annotation.Nullable;

import org.osgi.framework.BundleException;

/**
 * Thrown when an application tries to load in a bundle through its name.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public class BundleNotFoundException extends BundleException {
  private static final long serialVersionUID = 0L;

  /**
   * Creates a new instance with no detail message.
   */
  public BundleNotFoundException() {
    super(null);
  }

  /**
   * Creates a new instance with the given detail message.
   */
  public BundleNotFoundException(@Nullable final String message) {
    super(message);
  }

  /**
   * Creates a new instance with the given detail message and cause.
   */
  public BundleNotFoundException(@Nullable final String message, @Nullable final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance with the given cause.
   */
  public BundleNotFoundException(@Nullable final Throwable cause) {
    super(null, cause);
  }
}
