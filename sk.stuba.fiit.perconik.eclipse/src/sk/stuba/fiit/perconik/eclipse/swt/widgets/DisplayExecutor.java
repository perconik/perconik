package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import com.google.common.base.Supplier;

import org.eclipse.swt.widgets.Display;

import static com.google.common.base.Preconditions.checkNotNull;

import static com.google.common.base.Throwables.propagate;

public abstract class DisplayExecutor implements Executor {
  final Display display;

  DisplayExecutor(final Display display) {
    this.display = checkNotNull(display);
  }

  public static DisplayExecutor defaultAsynchronous() {
    return createAsynchronous(Display.getDefault());
  }

  public static DisplayExecutor defaultSynchronous() {
    return createSynchronous(Display.getDefault());
  }

  public static DisplayExecutor currentAsynchronous() {
    return createAsynchronous(Display.getCurrent());
  }

  public static DisplayExecutor currentSynchronous() {
    return createSynchronous(Display.getCurrent());
  }

  public static DisplayExecutor createAsynchronous(final Display display) {
    return new Asynchronous(display);
  }

  public static DisplayExecutor createSynchronous(final Display display) {
    return new Synchronous(display);
  }

  static final class Asynchronous extends DisplayExecutor {
    Asynchronous(final Display display) {
      super(display);
    }

    @Override
    public void execute(final Runnable command) {
      this.display.asyncExec(command);
    }

    @Override
    <V> Supplier<V> submit(final DisplayTask<V> task) {
      throw new UnsupportedOperationException("Unable to asynchronously execute display task");
    }
  }

  static final class Synchronous extends DisplayExecutor {
    Synchronous(final Display display) {
      super(display);
    }

    private static final class Call<V> implements Runnable, Supplier<V> {
      private final Callable<V> callable;

      private V result;

      Call(final Callable<V> callable) {
        this.callable = checkNotNull(callable);
      }

      public void run() {
        try {
          this.result = this.callable.call();
        } catch (Throwable failure) {
          propagate(failure);
        }
      }

      public V get() {
        return this.result;
      }
    }

    @Override
    public void execute(final Runnable command) {
      this.display.syncExec(command);
    }

    @Override
    <V> Supplier<V> submit(final DisplayTask<V> task) {
      Call<V> call = new Call<>(task);

      this.execute(call);

      return call;
    }
  }

  abstract <V> Supplier<V> submit(DisplayTask<V> task);

  public final Display getDisplay() {
    return this.display;
  }
}
