package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.Listener;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class AbstractHook<T, L extends Listener> extends Adapter implements Hook<T, L> {
  private final Pool<T> pool;

  AbstractHook(final Pool<T> pool) {
    this.pool = checkNotNull(pool);
  }

  public final void add(final T object) {
    this.pool.add(object);
  }

  public final void remove(final T object) {
    this.pool.remove(object);
  }

  public final Collection<T> toCollection() {
    return this.pool.toCollection();
  }
}
