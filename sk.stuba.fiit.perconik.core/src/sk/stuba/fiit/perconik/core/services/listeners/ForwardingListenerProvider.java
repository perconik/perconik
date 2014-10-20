package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.ForwardingProvider;

/**
 * A listener provider which forwards all its method calls to another listener
 * provider. Subclasses should override one or more methods to modify the
 * behavior of the backing listener provider as desired per the decorator
 * pattern.
 *
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object. See
 * {@link com.google.common.collect.ForwardingObject ForwardingObject}
 * for more details.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingListenerProvider extends ForwardingProvider implements ListenerProvider {
  /**
   * Constructor for use by subclasses.
   */
  protected ForwardingListenerProvider() {}

  @Override
  protected abstract ListenerProvider delegate();

  public <L extends Listener> L forClass(final Class<L> type) {
    return this.delegate().forClass(type);
  }

  public Class<? extends Listener> loadClass(final String name) throws ClassNotFoundException {
    return this.delegate().loadClass(name);
  }

  public Set<Class<? extends Listener>> classes() {
    return this.delegate().classes();
  }

  @Override
  public ListenerProvider parent() {
    return this.delegate().parent();
  }
}
