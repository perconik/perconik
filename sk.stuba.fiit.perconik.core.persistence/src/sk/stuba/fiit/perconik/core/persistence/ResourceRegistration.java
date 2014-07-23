package sk.stuba.fiit.perconik.core.persistence;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

/**
 * Current registration status of a {@code Resource} instance.
 * The current registration status is obtained from the underlying resource.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceRegistration extends AnnotableRegistration {
  /**
   * Returns the listener type to which
   * is the underlying resource registered. 
   */
  public Class<? extends Listener> getListenerType();

  /**
   * Returns the underlying resource. 
   */
  public Resource<?> getResource();

  /**
   * Returns the underlying resource name.
   */
  public String getResourceName();

  /**
   * Compares the specified object with this resource registration
   * for equality. Returns {@code true} if the specified object is also
   * a resource registration and the two registrations have the same listener
   * type and resource name. This definition ensures that this method works
   * properly across different implementations of the resource registration
   * interface.
   * @param o an object to be compared for equality
   *          with this resource registration
   * @return {@code true} if the specified object is equal to
   *         this resource registration, {@code false} otherwise
   */
  @Override
  public boolean equals(@Nullable Object o);

  /**
   * Returns the hash code value for this resource registration.
   * The hash code of a resource registration should be equivalent
   * to the result of {@link java.util.Objects#hash(Object...)} for
   * listener type and resource name.
   * @return the hash code value for this resource registration
   */
  @Override
  public int hashCode();
}
