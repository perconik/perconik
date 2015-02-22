package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import com.google.common.base.Stopwatch;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.utilities.concurrent.NamedRunnable;

import static java.util.Objects.requireNonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newLinkedList;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractEventListener.RegistrationHook.POST_UNREGISTER;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getWorkbench;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForWorkbench;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractEventListener implements Listener {
  final ListMultimap<RegistrationHook, Runnable> registerHooks;

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractEventListener() {
    this.registerHooks = LinkedListMultimap.create(RegistrationHook.values().length);

    Disposer.OnWorkbenchShutdown.activate(this);
    Disposer.OnFinalUnregistration.activate(this);
  }

  protected abstract <V> V execute(final DisplayTask<V> task);

  protected abstract void execute(final Runnable command);

  public enum RegistrationHook {
    PRE_REGISTER {
      @Override
      void handle(final AbstractEventListener listener, final Runnable task, final Exception failure) {
        listener.preRegisterFailure(task, failure);
      }
    },

    POST_REGISTER {
      @Override
      void handle(final AbstractEventListener listener, final Runnable task, final Exception failure) {
        listener.postRegisterFailure(task, failure);
      }
    },

    PRE_UNREGISTER {
      @Override
      void handle(final AbstractEventListener listener, final Runnable task, final Exception failure) {
        listener.preUnregisterFailure(task, failure);
      }
    },

    POST_UNREGISTER {
      @Override
      void handle(final AbstractEventListener listener, final Runnable task, final Exception failure) {
        listener.postUnregisterFailure(task, failure);
      }
    };

    void on(final AbstractEventListener listener) {
      for (Runnable task: listener.registerHooks.get(this)) {
        try {
          task.run();
        } catch (Exception failure) {
          this.handle(listener, task, failure);
        }
      }
    }

    abstract void handle(AbstractEventListener listener, Runnable task, Exception failure);

    public boolean add(final AbstractEventListener listener, final Runnable task) {
      return listener.registerHooks.put(this, requireNonNull(task));
    }

    public boolean remove(final AbstractEventListener listener, final Runnable task) {
      return listener.registerHooks.remove(this, requireNonNull(task));
    }
  }

  @Override
  public final void preRegister() {
    RegistrationHook.PRE_REGISTER.on(this);
  }

  protected abstract void preRegisterFailure(Runnable task, Exception failure);

  @Override
  public final void postRegister() {
    RegistrationHook.POST_REGISTER.on(this);
  }

  protected abstract void postRegisterFailure(Runnable task, Exception failure);

  @Override
  public final void preUnregister() {
    RegistrationHook.PRE_UNREGISTER.on(this);
  }

  protected abstract void preUnregisterFailure(Runnable task, Exception failure);

  @Override
  public final void postUnregister() {
    RegistrationHook.POST_UNREGISTER.on(this);
  }

  protected abstract void postUnregisterFailure(Runnable task, Exception failure);

  protected abstract class InternalProbe<T> implements Probe<T> {
    /**
     * Constructor for use by subclasses.
     */
    protected InternalProbe() {}
  }

  protected abstract Map<String, InternalProbe<?>> internalProbeMappings();

  static abstract class ContinuousEventWindow<E> {
    private final Object lock = new Object();

    @GuardedBy("lock")
    private final Stopwatch watch;

    @GuardedBy("lock")
    private LinkedList<E> sequence;

    final long window;

    final TimeUnit unit;

    ContinuousEventWindow(final Stopwatch watch, final long window, final TimeUnit unit) {
      this.watch = requireNonNull(watch);

      checkArgument(window >= 0L);

      this.window = window;
      this.unit = requireNonNull(unit);
    }

    @GuardedBy("lock")
    private void startWatchAndClearContinuousEvents() {
      assert !this.watch.isRunning() && this.sequence == null;

      this.sequence = newLinkedList();

      this.watch.reset().start();
    }

    @GuardedBy("lock")
    private void stopWatchAndProcessContinuousEvents() {
      assert this.watch.isRunning() && this.sequence != null;

      this.process(newLinkedList(this.sequence));

      this.sequence = null;

      this.watch.stop();
    }

    @GuardedBy("lock")
    private void restartWatch() {
      assert this.watch.isRunning();

      this.watch.reset().start();
    }

    public final LinkedList<E> sequence() {
      synchronized (this.lock) {
        return newLinkedList(this.sequence);
      }
    }

    public final void push(final E event) {
      synchronized (this.lock) {
        if (!this.accept(newLinkedList(this.sequence), event)) {
          return;
        }

        if (this.watch.isRunning() && !this.continuous(newLinkedList(this.sequence), event)) {
          this.watchRunningButEventsNotContinouous();
          this.stopWatchAndProcessContinuousEvents();
        }

        if (!this.watch.isRunning()) {
          this.watchNotRunning();
          this.startWatchAndClearContinuousEvents();
        }

        long delta = this.watch.elapsed(this.unit);

        this.sequence.add(event);

        if (delta < this.window) {
          this.watchWindowNotElapsed(delta);
          this.restartWatch();

          return;
        }

        this.stopWatchAndProcessContinuousEvents();
      }
    }

    public final void flush() {
      synchronized (this.lock) {
        if (this.watch.isRunning()) {
          this.stopWatchAndProcessContinuousEvents();
        }
      }
    }

    protected abstract boolean accept(LinkedList<E> sequence, E event);

    protected abstract boolean continuous(LinkedList<E> sequence, E event);

    protected abstract void process(LinkedList<E> sequence);

    /**
     * Invoked when watch running but events not continuous.
     */
    protected void watchRunningButEventsNotContinouous() {}

    /**
     * Invoked when watch not running.
     */
    protected void watchNotRunning() {}

    /**
     * Invoked when window not elapsed.
     *
     * @param delta elapsed time delta
     */
    protected void watchWindowNotElapsed(final long delta) {}
  }

  protected abstract void inject(String path, Event data) throws Exception;

  protected abstract void validate(String path, Event data) throws Exception;

  protected abstract void persist(String path, Event data) throws Exception;

  protected final void send(final String path, final Event data) {
    this.preSend(path, data);

    try {
      this.inject(path, data);
      this.validate(path, data);
      this.persist(path, data);
    } catch (Exception failure) {
      this.sendFailure(path, data, failure);
    }

    this.postSend(path, data);
  }

  protected final void send(final String path, final Iterable<Event> batch) {
    this.send(path, batch.iterator());
  }

  protected final void send(final String path, final Iterator<Event> batch) {
    while (batch.hasNext()) {
      this.send(path, batch.next());
    }
  }

  protected abstract void sendFailure(String path, Event data, Exception failure);

  abstract void preSend(String path, Event data);

  abstract void postSend(String path, Event data);

  @Override
  public final boolean equals(@Nullable final Object object) {
    return super.equals(object);
  }

  @Override
  public final int hashCode() {
    return super.hashCode();
  }

  /**
   * Always throws {@code CloneNotSupportedException}.
   */
  @Override
  protected final Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  /**
   * Listeners should not depend on Java finalization.
   */
  @Override
  protected final void finalize() {}

  private static abstract class Disposer {
    private static final Logger logger = Logger.getLogger(Disposer.class.getName());

    final AbstractEventListener listener;

    Disposer(final AbstractEventListener listener) {
      this.listener = requireNonNull(listener);
    }

    private static final class OnWorkbenchShutdown extends Disposer implements IWorkbenchListener {
      private OnWorkbenchShutdown(final AbstractEventListener listener) {
        super(listener);
      }

      static void activate(final AbstractEventListener listener) {
        final OnWorkbenchShutdown disposer = new OnWorkbenchShutdown(listener);

        final Runnable activation = new NamedRunnable(OnWorkbenchShutdown.class) {
          public void run() {
            waitForWorkbench().addWorkbenchListener(disposer);
          }
        };

        Display.getDefault().asyncExec(activation);
      }

      public boolean preShutdown(final IWorkbench workbench, final boolean forced) {
        return true;
      }

      public void postShutdown(final IWorkbench workbench) {
        this.safeDispose(workbench);
      }

      @Override
      void unsafeDispose() throws Exception {
        this.listener.onWorkbenchShutdown();
      }
    }

    private static final class OnFinalUnregistration extends Disposer {
      private OnFinalUnregistration(final AbstractEventListener listener) {
        super(listener);
      }

      static void activate(final AbstractEventListener listener) {
        final OnFinalUnregistration disposer = new OnFinalUnregistration(listener);

        final Runnable activation = new NamedRunnable(OnFinalUnregistration.class) {
          public void run() {
            disposer.safeDispose(getWorkbench());
          }
        };

        POST_UNREGISTER.add(listener, activation);
      }

      @Override
      void unsafeDispose() throws Exception {
        this.listener.onFinalUnregistration();
      }
    }

    void safeDispose(@Nullable final IWorkbench workbench) {
      if (workbench != null && workbench.isClosing()) {
        try {
          this.unsafeDispose();
        } catch (Exception failure) {
          logger.log(Level.INFO, "Internal failure on " + this.listener + " disposal", failure);
        }
      }
    }

    abstract void unsafeDispose() throws Exception;
  }

  protected abstract void onWorkbenchShutdown() throws Exception;

  protected abstract void onFinalUnregistration() throws Exception;

  @Override
  public String toString() {
    return this.getClass().getName();
  }
}
