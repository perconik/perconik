package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.concurrent.Executor;

import org.eclipse.swt.widgets.Display;

import static com.google.common.base.Preconditions.checkNotNull;

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

  private static final class Asynchronous extends DisplayExecutor {
    Asynchronous(final Display display) {
      super(display);
    }

    @Override
    public void execute(final Runnable command) {
      this.display.asyncExec(command);
    }
  }

  private static final class Synchronous extends DisplayExecutor {
    Synchronous(final Display display) {
      super(display);
    }

    @Override
    public void execute(final Runnable command) {
      this.display.syncExec(command);
    }
  }

  public final Display getDisplay() {
    return this.display;
  }
}
