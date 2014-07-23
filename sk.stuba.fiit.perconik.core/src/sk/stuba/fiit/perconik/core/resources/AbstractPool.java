package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class AbstractPool<T> implements Pool<T> {
  final Collection<T> objects;

  final Handler<T> handler;

  AbstractPool(final Collection<T> implementation, final Handler<T> handler) {
    this.objects = checkNotNull(implementation);
    this.handler = checkNotNull(handler);
  }

  public final void add(final T object) {
    if (!this.contains(object)) {
      this.handler.register(object);
      this.objects.add(object);
    }
  }

  public final void remove(final T object) {
    this.handler.unregister(object);
    this.objects.remove(object);
  }
}
