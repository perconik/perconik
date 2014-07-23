package sk.stuba.fiit.perconik.utilities.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import com.google.common.base.Supplier;
import com.google.common.base.Throwables;

import sk.stuba.fiit.perconik.utilities.MoreSuppliers;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Executables {
  private Executables() {
    throw new AssertionError();
  }

  static final class SafeCall<V> implements Runnable {
    private final Callable<V> callable;

    private V result;

    private Exception failure;

    SafeCall(Callable<V> callable) {
      this.callable = checkNotNull(callable);
    }

    public final void run() {
      try {
        this.result = this.callable.call();
      } catch (Exception failure) {
        this.failure = failure;
      }
    }

    final V getChecked() throws Exception {
      if (this.failure != null) {
        throw this.failure;
      }

      return this.result;
    }

    final V getUnchecked() {
      if (this.failure != null) {
        throw Throwables.propagate(this.failure);
      }

      return this.result;
    }
  }

  public static final <E extends Runnable> E execute(Executor executor, E executable) {
    executor.execute(checkNotNull(executable));

    return executable;
  }

  public static final <V> V call(Executor executor, Callable<V> callable) {
    return execute(executor, new SafeCall<>(callable)).getUnchecked();
  }

  public static final <V> V call(Executor executor, Supplier<V> supplier) {
    return call(executor, MoreSuppliers.asCallable(supplier));
  }

  public static final <V> V check(Executor executor, Callable<V> callable) throws Exception {
    return execute(executor, new SafeCall<>(callable)).getChecked();
  }

  public static final <V> V check(Executor executor, Supplier<V> supplier) throws Exception {
    return check(executor, MoreSuppliers.asCallable(supplier));
  }

  public static final void run(Executor executor, Runnable runnable) {
    execute(executor, runnable);
  }
}
