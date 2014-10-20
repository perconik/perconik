package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.ForwardingNameable;

/**
 * A provider which forwards all its method calls to another provider.
 * Subclasses should override one or more methods to modify the behavior
 * of the backing provider as desired per the decorator pattern.
 *
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object.
 * See {@code ForwardingObject} for more details.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingProvider extends ForwardingNameable implements Provider {
  /**
   * Constructor for use by subclasses.
   */
  protected ForwardingProvider() {}

  @Override
  protected abstract Provider delegate();

  public Provider parent() {
    return this.delegate().parent();
  }
}
