package sk.stuba.fiit.perconik.core.resources;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class AbstractListenerProxy<L> {
  final L listener;

  AbstractListenerProxy(final L listener) {
    this.listener = checkNotNull(listener);
  }

  @Override
  public final boolean equals(@Nullable final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof AbstractListenerProxy)) {
      return false;
    }

    AbstractListenerProxy<?> other = (AbstractListenerProxy<?>) o;

    return this.listener.equals(other.listener);
  }

  @Override
  public final int hashCode() {
    return this.listener.hashCode();
  }

  @Override
  public final String toString() {
    return this.listener.toString();
  }
}
