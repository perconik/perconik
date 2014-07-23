package sk.stuba.fiit.perconik.utilities;

import java.util.concurrent.Callable;

import com.google.common.base.Supplier;

public final class MoreSuppliers {
  private MoreSuppliers() {
    throw new AssertionError();
  }

  public static final <V> Callable<V> asCallable(final Supplier<V> supplier) {
    return new Callable<V>() {
      public final V call() throws Exception {
        return supplier.get();
      }
    };
  }
}
