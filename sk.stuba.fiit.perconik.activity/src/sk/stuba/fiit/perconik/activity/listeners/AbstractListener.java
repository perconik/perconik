package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
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

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newLinkedList;

import static sk.stuba.fiit.perconik.activity.listeners.AbstractListener.RegistrationHook.POST_UNREGISTER;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getWorkbench;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForWorkbench;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractListener implements Listener {
  final ListMultimap<RegistrationHook, Runnable> registerHooks;

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractListener() {
    this.registerHooks = LinkedListMultimap.create(RegistrationHook.values().length);

    Disposer.OnWorkbenchShutdown.activate(this);
    Disposer.OnFinalUnregistration.activate(this);
  }

  protected abstract <V> V execute(final DisplayTask<V> task);

  protected abstract void execute(final Runnable command);

  public enum RegistrationHook {
    PRE_REGISTER {
      @Override
      void handle(final AbstractListener listener, final Runnable task, final Exception failure) {
        listener.preRegisterFailure(task, failure);
      }
    },

    POST_REGISTER {
      @Override
      void handle(final AbstractListener listener, final Runnable task, final Exception failure) {
        listener.postRegisterFailure(task, failure);
      }
    },

    PRE_UNREGISTER {
      @Override
      void handle(final AbstractListener listener, final Runnable task, final Exception failure) {
        listener.preUnregisterFailure(task, failure);
      }
    },

    POST_UNREGISTER {
      @Override
      void handle(final AbstractListener listener, final Runnable task, final Exception failure) {
        listener.postUnregisterFailure(task, failure);
      }
    };

    void on(final AbstractListener listener) {
      for (Runnable task: listener.registerHooks.get(this)) {
        try {
          task.run();
        } catch (Exception failure) {
          this.handle(listener, task, failure);
        }
      }
    }

    abstract void handle(AbstractListener listener, Runnable task, Exception failure);

    public boolean add(final AbstractListener listener, final Runnable task) {
      return listener.registerHooks.put(this, requireNonNull(task));
    }

    public boolean remove(final AbstractListener listener, final Runnable task) {
      return listener.registerHooks.remove(this, requireNonNull(task));
    }

    @Override
    public String toString() {
      return this.name().toLowerCase();
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

  public static abstract class ContinuousEvent<E> {
    private static final long UNSET = -1L;

    private final Object lock = new Object();

    @GuardedBy("lock")
    private final Stopwatch watch;

    @GuardedBy("lock")
    private List<E> sequence;

    @GuardedBy("lock")
    private long total;

    final long pause;

    final long window;

    final TimeUnit unit;

    protected ContinuousEvent(final Stopwatch watch, final long pause, final long window, final TimeUnit unit) {
      this.watch = requireNonNull(watch);
      this.sequence = newLinkedList();
      this.total = UNSET;

      checkArgument(pause >= 0L);
      checkArgument(window >= 0L);

      this.pause = pause;
      this.window = window;
      this.unit = requireNonNull(unit);
    }

    @GuardedBy("lock")
    private void startWatchAndClearContinuousEvents() {
      assert !this.watch.isRunning() && this.sequence.isEmpty() && this.total == UNSET;

      this.sequence = newLinkedList();
      this.total = 0L;

      this.watch.reset().start();
    }

    @GuardedBy("lock")
    private void stopWatchAndProcessContinuousEvents() {
      assert this.watch.isRunning() && !this.sequence.isEmpty() && this.total != UNSET;

      this.process(newLinkedList(this.sequence));

      this.sequence = emptyList();
      this.total = UNSET;

      this.watch.stop();
    }

    @GuardedBy("lock")
    private void restartWatch() {
      assert this.watch.isRunning();

      this.watch.reset().start();
    }

    public abstract void push(E event);

    public abstract void flush();

    final void synchronizedPush(final E event) {
      synchronized (this.lock) {
        LinkedList<E> sequence = newLinkedList(this.sequence);

        if (!this.accept(sequence, event)) {
          return;
        }

        if (this.watch.isRunning() && !this.continuous(sequence, event)) {
          this.watchRunningButEventsNotContinouous();
          this.stopWatchAndProcessContinuousEvents();
        }

        if (!this.watch.isRunning()) {
          this.watchNotRunning();
          this.startWatchAndClearContinuousEvents();
        }

        long delta = this.watch.elapsed(this.unit);
        long total = this.total += delta;

        this.sequence.add(event);

        if (delta < this.pause && total < this.window) {
          this.watchTimeNotElapsed(delta);
          this.restartWatch();

          return;
        }

        this.watchTimeElapsedAndAboutToProcess(delta);
        this.stopWatchAndProcessContinuousEvents();
      }
    }

    final void synchronizedFlush() {
      synchronized (this.lock) {
        if (this.watch.isRunning()) {
          this.stopWatchAndProcessContinuousEvents();
        }
      }
    }

    public final LinkedList<E> sequence() {
      synchronized (this.lock) {
        return newLinkedList(this.sequence);
      }
    }

    /**
     * Returns maximal between continuous event pause.
     */
    public final long pause() {
      return this.pause;
    }

    /**
     * Returns maximal continuous event time window.
     */
    public final long window() {
      return this.window;
    }

    /**
     * Returns common time unit for both pause and window.
     */
    public final TimeUnit unit() {
      return this.unit;
    }

    /**
     * Determines whether specified event is suitable for further processing.
     *
     * @param sequence possibly empty continuous event sequence
     * @param event potential event to further process
     */
    protected abstract boolean accept(LinkedList<E> sequence, E event);

    /**
     * Determines whether specified event is continuous to the event sequence.
     *
     * @param sequence non-empty continuous event sequence
     * @param event potential event sequence candidate
     */
    protected abstract boolean continuous(LinkedList<E> sequence, E event);

    /**
     * Processes continuous event sequence.
     *
     * @param sequence non-empty continuous event sequence
     */
    protected abstract void process(LinkedList<E> sequence);

    /**
     * Returns total elapsed window time.
     *
     * @throws IllegalStateException if watch not running
     */
    protected final long total() {
      long total = this.total;

      checkState(total >= 0L);

      return total;
    }

    /**
     * Invoked when watch running but events not continuous so about to be processed.
     */
    protected void watchRunningButEventsNotContinouous() {}

    /**
     * Invoked when watch not running and about to start.
     */
    protected void watchNotRunning() {}

    /**
     * Invoked when watch time not elapsed and about to wait for next event push.
     *
     * @param delta elapsed time delta lesser than pause
     */
    protected void watchTimeNotElapsed(final long delta) {}

    /**
     * Invoked when watch time elapsed and continuous events about to be processed.
     *
     * @param delta elapsed time delta greater or equal to pause
     */
    protected void watchTimeElapsedAndAboutToProcess(final long delta) {}

    @Override
    public String toString() {
      return this.toStringHelper().toString();
    }

    protected ToStringHelper toStringHelper() {
      ToStringHelper helper = Objects.toStringHelper(this);

      helper.add("pause", this.pause);
      helper.add("window", this.window);
      helper.add("unit", this.unit);

      return helper;
    }
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

    final AbstractListener listener;

    Disposer(final AbstractListener listener) {
      this.listener = requireNonNull(listener);
    }

    private static final class OnWorkbenchShutdown extends Disposer implements IWorkbenchListener {
      private OnWorkbenchShutdown(final AbstractListener listener) {
        super(listener);
      }

      static void activate(final AbstractListener listener) {
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
      private OnFinalUnregistration(final AbstractListener listener) {
        super(listener);
      }

      static void activate(final AbstractListener listener) {
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

  /**
   * Invoked automatically on workbench shutdown.
   *
   * <p><b>Warning:</b> users should not invoke this method directly.
   */
  protected abstract void onWorkbenchShutdown() throws Exception;

  /**
   * Invoked automatically on final unregistration when workbench is closing.
   *
   * <p><b>Warning:</b> users should not invoke this method directly.
   */
  protected abstract void onFinalUnregistration() throws Exception;

  @Override
  public String toString() {
    return this.getClass().getName();
  }
}
