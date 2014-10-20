package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;

final class Pools {
  private enum DefaultPoolFactory implements PoolFactory {
    INSTANCE;

    public <T> Pool<T> create(final Handler<T> handler) {
      return Synchronized.pool(GenericPool.builder(handler).identity().hashSet().build());
    }
  }

  private static PoolFactory objectPoolFactory = DefaultPoolFactory.INSTANCE;

  private static PoolFactory listenerPoolFactory = DefaultPoolFactory.INSTANCE;

  private Pools() {}

  private static final class SafePool<T> implements Pool<T> {
    private final Pool<T> pool;

    private final Class<T> type;

    SafePool(final Pool<T> pool, final Class<T> type) {
      this.pool = checkNotNull(pool);
      this.type = checkNotNull(type);
    }

    private T check(final T object) {
      return this.type.cast(checkNotNull(object));
    }

    public boolean contains(final Object object) {
      return this.pool.contains(object);
    }

    public void add(final T object) {
      this.pool.add(this.check(object));
    }

    public void remove(final T object) {
      this.pool.remove(this.check(object));
    }

    public Collection<T> toCollection() {
      return this.pool.toCollection();
    }

    @Override
    public String toString() {
      return this.pool.toString();
    }
  }

  static <T> Pool<T> safe(final Pool<T> pool, final Class<T> type) {
    return new SafePool<>(pool, type);
  }

  static void setObjectPoolFactory(final PoolFactory factory) {
    objectPoolFactory = checkNotNull(factory);
  }

  static void setListenerPoolFactory(final PoolFactory factory) {
    listenerPoolFactory = checkNotNull(factory);
  }

  static PoolFactory getObjectPoolFactory() {
    return objectPoolFactory;
  }

  static PoolFactory getListenerPoolFactory() {
    return listenerPoolFactory;
  }
}
