package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Collection;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.ForwardingManager;

/**
 * A listener manager which forwards all its method calls to another listener
 * manager. Subclasses should override one or more methods to modify the
 * behavior of the backing listener manager as desired per the decorator
 * pattern.
 *
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object. See
 * {@link com.google.common.collect.ForwardingObject ForwardingObject}
 * for more details.
 *
 * @see ListenerManager
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingListenerManager extends ForwardingManager implements ListenerManager {
  /**
   * Constructor for use by subclasses.
   */
  protected ForwardingListenerManager() {}

  @Override
  protected abstract ListenerManager delegate();

  public <L extends Listener> void register(final L listener) {
    this.delegate().register(listener);
  }

  public <L extends Listener> void unregister(final L listener) {
    this.delegate().unregister(listener);
  }

  public <L extends Listener> void unregisterAll(final Class<L> type) {
    this.delegate().unregisterAll(type);
  }

  public <L extends Listener> Collection<L> registered(final Class<L> type) {
    return this.delegate().registered(type);
  }

  public SetMultimap<Resource<?>, Listener> registrations() {
    return this.delegate().registrations();
  }
}
