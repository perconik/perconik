package sk.stuba.fiit.perconik.core.services;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.collect.MapMaker;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;

final class Internals {
  private static final Map<Class<?>, Supplier<?>> suppliers = new MapMaker().concurrencyLevel(1).makeMap();

  private Internals() {
    throw new AssertionError();
  }

  private static final class ImmutableReference<T> implements Supplier<T> {
    private final T object;

    ImmutableReference(final T object) {
      this.object = object;
    }

    public final T get() {
      return this.object;
    }
  }

  static final <T> void setApi(final Class<T> api, final T implementation) {
    checkNotNull(implementation);

    suppliers.put(api, new ImmutableReference<>(implementation));
  }

  static final <T> void setApi(final Class<T> api, final Supplier<T> supplier) {
    checkNotNull(supplier);

    suppliers.put(api, supplier);
  }

  static final <T> T getApi(final Class<T> api) {
    T implementation = api.cast(suppliers.get(api).get());

    if (implementation != null) {
      return implementation;
    }

    throw new UnsupportedOperationException("Unable to get implementation for " + api);
  }

  static final <T> Set<T> getApis(final Class<T> superclass) {
    Set<T> implementations = newHashSet();

    for (Supplier<?> supplier: suppliers.values()) {
      Object implementation = supplier.get();

      if (superclass.isInstance(implementation)) {
        implementations.add(superclass.cast(implementation));
      }
    }

    return implementations;
  }
}
