package sk.stuba.fiit.perconik.core.services;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Suppliers.ofInstance;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

final class Internals {
  private static Map<Class<?>, Supplier<?>> suppliers = newHashMap();

  private Internals() {}

  static <T> void setApi(final Class<T> api, final T implementation) {
    suppliers.put(checkNotNull(api), ofInstance(checkNotNull(implementation)));
  }

  static <T> void setApi(final Class<T> api, final Supplier<? extends T> supplier) {
    suppliers.put(checkNotNull(api), checkNotNull(supplier));
  }

  static <T> void unsetApi(final Class<T> api) {
    suppliers.remove(checkNotNull(api));
  }

  static <T> T getApi(final Class<T> api) {
    Supplier<?> supplier = suppliers.get(api);

    if (supplier != null) {
      return checkNotNull(api.cast(supplier.get()));
    }

    throw new UnsupportedOperationException("Unable to get implementation of " + api);
  }

  static <T> Set<T> getApis(final Class<T> type) {
    checkNotNull(type);

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
