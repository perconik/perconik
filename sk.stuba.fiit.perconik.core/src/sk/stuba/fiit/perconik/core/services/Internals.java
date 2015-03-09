package sk.stuba.fiit.perconik.core.services;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.collect.MapMaker;

import static java.util.Objects.requireNonNull;

import static com.google.common.base.Suppliers.ofInstance;
import static com.google.common.collect.Sets.newHashSet;

final class Internals {
  private static Map<Class<?>, Supplier<?>> suppliers = new MapMaker().concurrencyLevel(1).makeMap();

  private Internals() {}

  static <T> void setApi(final Class<T> api, final T implementation) {
    suppliers.put(requireNonNull(api), ofInstance(requireNonNull(implementation)));
  }

  static <T> void setApi(final Class<T> api, final Supplier<? extends T> supplier) {
    suppliers.put(requireNonNull(api), requireNonNull(supplier));
  }

  static <T> void unsetApi(final Class<T> api) {
    suppliers.remove(requireNonNull(api));
  }

  static <T> T getApi(final Class<T> api) {
    Supplier<?> supplier = suppliers.get(api);

    if (supplier != null) {
      return requireNonNull(api.cast(supplier.get()));
    }

    throw new UnsupportedOperationException("Unable to get implementation of " + api);
  }

  static <T> Set<T> getApis(final Class<T> type) {
    requireNonNull(type);

    Set<T> implementations = newHashSet();

    for (Supplier<?> supplier: suppliers.values()) {
      Object implementation = supplier.get();

      if (type.isInstance(implementation)) {
        implementations.add(type.cast(implementation));
      }
    }

    return implementations;
  }
}
