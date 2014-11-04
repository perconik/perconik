package sk.stuba.fiit.perconik.activity.listeners;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.probes.Probe;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static java.util.Objects.requireNonNull;

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
    Disposer.activate(this);
  }

  protected abstract <V> V execute(final DisplayTask<V> task);

  protected abstract void execute(final Runnable command);

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

  abstract void preSend(final String path, final Event data);

  abstract void postSend(final String path, final Event data);

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

  protected abstract void sendFailure(final String path, final Event data, Exception failure);

  private static final class Disposer implements IWorkbenchListener {
    private static final Logger logger = Logger.getLogger(Disposer.class.getName());

    private final AbstractEventListener listener;

    private Disposer(final AbstractEventListener listener) {
      this.listener = requireNonNull(listener);
    }

    static void activate(final AbstractEventListener listener) {
      final Disposer disposer = new Disposer(listener);

      final Runnable activation = new Runnable() {
        public void run() {
          waitForWorkbench().addWorkbenchListener(disposer);
        }
      };

      Display.getDefault().asyncExec(activation);
    }

    public final boolean preShutdown(final IWorkbench workbench, final boolean forced) {
      return true;
    }

    public void postShutdown(final IWorkbench workbench) {
      try {
        this.listener.dispose();
      } catch (Exception failure) {
        logger.log(Level.INFO, "Internal failure on " + this.listener + " disposal", failure);
      }
    }
  }

  protected abstract void dispose() throws Exception;
}
