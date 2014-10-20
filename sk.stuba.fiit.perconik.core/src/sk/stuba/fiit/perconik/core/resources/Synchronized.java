package sk.stuba.fiit.perconik.core.resources;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Registrable;
import sk.stuba.fiit.perconik.core.Resource;

import static com.google.common.base.Preconditions.checkNotNull;

final class Synchronized {
  private Synchronized() {}

  private static class SynchronizedObject<T> implements Serializable {
    private static final long serialVersionUID = 0L;

    final T delegate;

    final Object mutex;

    SynchronizedObject(final T delegate, final Object mutex) {
      this.delegate = checkNotNull(delegate);
      this.mutex = (mutex == null) ? this : mutex;
    }

    @Override
    public final String toString() {
      synchronized (this.mutex) {
        return this.delegate.toString();
      }
    }

    private final void writeObject(final ObjectOutputStream stream) throws IOException {
      synchronized (this.mutex) {
        stream.defaultWriteObject();
      }
    }
  }

  private static class SynchronizedRegistrable<R extends Registrable> extends SynchronizedObject<R> implements Registrable {
    private static final long serialVersionUID = 0L;

    SynchronizedRegistrable(final R registrable, final Object mutex) {
      super(registrable, mutex);
    }

    public final void preRegister() {
      synchronized (this.mutex) {
        this.delegate.preRegister();
      }
    }

    public final void postRegister() {
      synchronized (this.mutex) {
        this.delegate.postRegister();
      }
    }

    public final void preUnregister() {
      synchronized (this.mutex) {
        this.delegate.preUnregister();
      }
    }

    public final void postUnregister() {
      synchronized (this.mutex) {
        this.delegate.preUnregister();
      }
    }
  }

  private static final class SynchronizedResource<L extends Listener> extends SynchronizedRegistrable<Resource<L>> implements Resource<L> {
    private static final long serialVersionUID = 0L;

    SynchronizedResource(final Resource<L> resource, final Object mutex) {
      super(resource, mutex);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (o == this) {
        return true;
      }

      synchronized (this.mutex) {
        return this.delegate.equals(o);
      }
    }

    @Override
    public int hashCode() {
      synchronized (this.mutex) {
        return this.delegate.hashCode();
      }
    }

    public void register(final L listener) {
      synchronized (this.mutex) {
        this.delegate.register(listener);
      }
    }

    public void unregister(final L listener) {
      synchronized (this.mutex) {
        this.delegate.register(listener);
      }
    }

    public <U extends Listener> Collection<U> registered(final Class<U> type) {
      synchronized (this.mutex) {
        return this.delegate.registered(type);
      }
    }

    public boolean registered(final Listener listener) {
      synchronized (this.mutex) {
        return this.delegate.registered(listener);
      }
    }

    public String getName() {
      synchronized (this.mutex) {
        return this.delegate.getName();
      }
    }
  }

  private static final class SynchronizedPool<T> extends SynchronizedObject<Pool<T>> implements Pool<T> {
    private static final long serialVersionUID = 0L;

    SynchronizedPool(final Pool<T> pool, final Object mutex) {
      super(pool, mutex);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (o == this) {
        return true;
      }

      synchronized (this.mutex) {
        return this.delegate.equals(o);
      }
    }

    @Override
    public int hashCode() {
      synchronized (this.mutex) {
        return this.delegate.hashCode();
      }
    }

    public boolean contains(final Object object) {
      synchronized (this.mutex) {
        return this.delegate.contains(object);
      }
    }

    public void add(final T object) {
      synchronized (this.mutex) {
        this.delegate.add(object);
      }
    }

    public void remove(final T object) {
      synchronized (this.mutex) {
        this.delegate.remove(object);
      }
    }

    public Collection<T> toCollection() {
      synchronized (this.mutex) {
        return this.delegate.toCollection();
      }
    }
  }

  private static final class SynchronizedHandler<T> extends SynchronizedObject<Handler<T>> implements Handler<T> {
    private static final long serialVersionUID = 0L;

    SynchronizedHandler(final Handler<T> handler, final Object mutex) {
      super(handler, mutex);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (o == this) {
        return true;
      }

      synchronized (this.mutex) {
        return this.delegate.equals(o);
      }
    }

    @Override
    public int hashCode() {
      synchronized (this.mutex) {
        return this.delegate.hashCode();
      }
    }

    public void register(final T object) {
      synchronized (this.mutex) {
        this.delegate.register(object);
      }
    }

    public void unregister(final T object) {
      synchronized (this.mutex) {
        this.delegate.unregister(object);
      }
    }
  }

  private static final class SynchronizedHook<T, L extends Listener> extends SynchronizedRegistrable<Hook<T, L>> implements Hook<T, L> {
    private static final long serialVersionUID = 0L;

    SynchronizedHook(final Hook<T, L> handler, final Object mutex) {
      super(handler, mutex);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (o == this) {
        return true;
      }

      synchronized (this.mutex) {
        return this.delegate.equals(o);
      }
    }

    @Override
    public int hashCode() {
      synchronized (this.mutex) {
        return this.delegate.hashCode();
      }
    }

    public void add(final T object) {
      synchronized (this.mutex) {
        this.delegate.add(object);
      }
    }

    public void remove(final T object) {
      synchronized (this.mutex) {
        this.delegate.remove(object);
      }
    }

    public Collection<T> toCollection() {
      synchronized (this.mutex) {
        return this.delegate.toCollection();
      }
    }

    public L forListener() {
      synchronized (this.mutex) {
        return this.delegate.forListener();
      }
    }
  }

  static <L extends Listener> Resource<L> resource(final Resource<L> resource) {
    return new SynchronizedResource<>(resource, new Object());
  }

  static <T> Pool<T> pool(final Pool<T> pool) {
    return new SynchronizedPool<>(pool, new Object());
  }

  static <T> Handler<T> handler(final Handler<T> handler) {
    return new SynchronizedHandler<>(handler, new Object());
  }

  static <T, L extends Listener> Hook<T, L> hook(final Hook<T, L> hook) {
    return new SynchronizedHook<>(hook, new Object());
  }
}
