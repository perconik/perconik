package sk.stuba.fiit.perconik.core.persistence.data;

import sk.stuba.fiit.perconik.core.Registrable;
import sk.stuba.fiit.perconik.core.persistence.Registration;

/**
 * An abstract implementation of the {@link Registration} interface.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractRegistration implements Registration {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractRegistration() {}

  /**
   * Always returns the underlying registrable object.
   */
  abstract Registrable registrable();
}
