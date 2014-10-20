package sk.stuba.fiit.perconik.utilities.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import com.google.common.base.Supplier;

import sk.stuba.fiit.perconik.utilities.MoreSuppliers;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;

public final class Executables {
  private Executables() {}

  static final class SafeCall<V> implements Runnable {
    private final Callable<V> callable;

    private V result;

    private Exception failure;

    SafeCall(final Callable<V> callable) {
      this.callable = checkNotNull(callable);
    }

    public void run() {
      try {
        this.result = this.callable.call();
      } catch (Exception failure) {
        this.failure = failure;
      }
    }

    V getChecked() throws Exception {
      if (this.failure != null) {
        throw this.failure;
      }

      return this.result;
    }

    V getUnchecked() {
      if (this.failure != null) {
        throw propagate(this.failure);
      }

      return this.result;
    }
  }

  public static <E extends Runnable> E execute(final Executor executor, final E executable) {
    executor.execute(checkNotNull(executable));

    return executable;
  }

  public static <V> V call(final Executor executor, final Callable<V> callable) {
    return execute(executor, new SafeCall<>(callable)).getUnchecked();
  }

  public static <V> V call(final Executor executor, final Supplier<V> supplier) {
    return call(executor, MoreSuppliers.asCallable(supplier));
  }

  public static <V> V check(final Executor executor, final Callable<V> callable) throws Exception {
    return execute(executor, new SafeCall<>(callable)).getChecked();
  }

  public static <V> V check(final Executor executor, final Supplier<V> supplier) throws Exception {
    return check(executor, MoreSuppliers.asCallable(supplier));
  }

  public static void run(final Executor executor, final Runnable runnable) {
    execute(executor, runnable);
  }
}
