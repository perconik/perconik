package sk.stuba.fiit.perconik.core;

import java.util.Collection;

/**
 * A resource which forwards all its method calls to another resource.
 * Subclasses should override one or more methods to modify the behavior
 * of the backing resource as desired per the decorator pattern.
 *
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object. See
 * {@link com.google.common.collect.ForwardingObject ForwardingObject}
 * for more details.
 *
 * @see Resource
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingResource<L extends Listener> extends ForwardingRegistrable implements Resource<L> {
  /**
   * Constructor for use by subclasses.
   */
  protected ForwardingResource() {}

  @Override
  protected abstract Resource<L> delegate();

  public void register(final L listener) {
    this.delegate().register(listener);
  }

  public void unregister(final L listener) {
    this.delegate().unregister(listener);
  }

  public <U extends Listener> Collection<U> registered(final Class<U> type) {
    return this.delegate().registered(type);
  }

  public boolean registered(final Listener listener) {
    return this.delegate().registered(listener);
  }

  public String getName() {
    return this.delegate().getName();
  }
}
