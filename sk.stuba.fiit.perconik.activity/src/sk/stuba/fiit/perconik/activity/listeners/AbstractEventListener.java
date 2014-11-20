package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getWorkbench;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForWorkbench;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractEventListener extends Adapter {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractEventListener() {
    Disposer.OnWorkbenchShutdown.activate(this);
  }

  protected abstract <V> V execute(final DisplayTask<V> task);

  protected abstract void execute(final Runnable command);

  @Override
  public final void preRegister() {
    this.preRegisterHook();
  }

  @Override
  public final void postUnregister() {
    this.postUnregisterHook();

    Disposer.OnFinalUnregistration.activate(this);
  }

  abstract void preRegisterHook();

  abstract void postUnregisterHook();

  protected abstract class InternalProbe<T> implements Probe<T> {
    /**
     * Constructor for use by subclasses.
     */
    protected InternalProbe() {}
  }

  protected abstract Map<String, InternalProbe<?>> internalProbeMappings();

  protected abstract void inject(final String path, final Event data) throws Exception;

  protected abstract void validate(final String path, final Event data) throws Exception;

  protected abstract void persist(final String path, final Event data) throws Exception;

  protected final void send(final String path, final Event data) {
    this.preSendHook(path, data);

    try {
      this.inject(path, data);
      this.validate(path, data);
      this.persist(path, data);
    } catch (Exception failure) {
      this.sendFailure(path, data, failure);
    }

    this.postSendHook(path, data);
  }

  protected final void send(final String path, final Iterable<Event> batch) {
    this.send(path, batch.iterator());
  }

  protected final void send(final String path, final Iterator<Event> batch) {
    while (batch.hasNext()) {
      this.send(path, batch.next());
    }
  }

  protected abstract void sendFailure(final String path, final Event data, Exception failure);

  abstract void preSendHook(final String path, final Event data);

  abstract void postSendHook(final String path, final Event data);

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

        final Runnable activation = new Runnable() {
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
        new OnFinalUnregistration(listener).safeDispose(getWorkbench());
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
