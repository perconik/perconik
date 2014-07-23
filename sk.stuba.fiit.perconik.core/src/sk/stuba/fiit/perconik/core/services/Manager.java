package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.Nameable;

/**
 * An object managing other objects, usually {@code Registrable} instances.
 * 
 * <p>Uniqueness of a manager is determined by its qualified name.
 * Two {@code Manager} instances with the same qualified name should
 * be implemented by the same class, provide the same functionality
 * and behave the same way.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Manager extends Nameable {
  /**
   * Compares the specified object with this manager for equality.
   * Returns {@code true} if the specified object is also manager
   * and the two managers have the same name. This definition ensures
   * that this method works properly across different implementations
   * of the manager interface.
   * @param o an object to be compared for equality with this manager
   * @return {@code true} if the specified object is equal to
   *         this manager, {@code false} otherwise
   */
  @Override
  public boolean equals(@Nullable Object o);

  /**
   * Returns the hash code value for this manager. The hash code of a
   * manager should be equivalent to the hash code of the manager's name.
   * @return the hash code value for this manager
   */
  @Override
  public int hashCode();
}
