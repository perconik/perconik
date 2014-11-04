package sk.stuba.fiit.perconik.utilities;

import java.util.concurrent.Callable;

import com.google.common.base.Supplier;

import static com.google.common.base.Suppliers.ofInstance;

public final class MoreSuppliers {
  private static final Supplier<Object> ofNull = ofInstance(null);

  private MoreSuppliers() {}

  public static <T> Supplier<T> ofNull() {
    @SuppressWarnings("unchecked")
    Supplier<T> casted = (Supplier<T>) ofNull;

    return casted;
  }

  public static <V> Callable<V> asCallable(final Supplier<V> supplier) {
    return new Callable<V>() {
      public V call() throws Exception {
        return supplier.get();
      }
    };
  }
}
