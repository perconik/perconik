package sk.stuba.fiit.perconik.activity.listeners.ui;

import com.google.common.base.Stopwatch;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.WindowSerializer;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.SECONDS;

import static sk.stuba.fiit.perconik.activity.listeners.ui.WindowListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.WindowListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.WindowListener.Action.DEACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.WindowListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForActiveWindow;
import static sk.stuba.fiit.perconik.utilities.concurrent.TimeValue.of;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.1.alpha")
public final class WindowListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.WindowListener, sk.stuba.fiit.perconik.core.listeners.WorkbenchListener {
  static final TimeValue windowOpenOnStartupTimeout = of(10, SECONDS);

  public WindowListener() {}

  enum Action implements CommonEventListener.Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "window", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final IWorkbenchWindow window) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("window"), new WindowSerializer(TREE).serialize(window));

    IWorkbench workbench = window.getWorkbench();

    data.put(key("window", "workbench"), identifyObject(workbench));

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchWindow window) {
    this.send(action.getPath(), build(time, action, window));
  }

  void execute(final long time, final Action action, final IWorkbench workbench) {
    this.execute(DisplayTask.of(new Runnable() {
      public void run() {
        process(time, action, waitForActiveWindow(workbench));
      }
    }));
  }

  void execute(final long time, final Action action, final IWorkbenchWindow window) {
    this.execute(DisplayTask.of(new Runnable() {
      public void run() {
        process(time, action, window);
      }
    }));
  }

  public void windowOpened(final IWorkbenchWindow window) {
    final long time = this.currentTime();

    this.execute(time, OPEN, window);
  }

  public void windowClosed(final IWorkbenchWindow window) {
    final long time = this.currentTime();

    if (!WorkbenchState.SHUTDOWN.isProcessed()) {
      this.execute(time, CLOSE, window);
    }
  }

  public void windowActivated(final IWorkbenchWindow window) {
    final long time = this.currentTime();

    this.execute(time, ACTIVATE, window);
  }

  public void windowDeactivated(final IWorkbenchWindow window) {
    final long time = this.currentTime();

    this.execute(time, DEACTIVATE, window);
  }

  public void postStartup(final IWorkbench workbench) {
    this.execute(new Runnable() {
      public void run() {
        if (!Listeners.registered(WorkbenchListener.class).isEmpty()) {
          return;
        }

        TimeHelper helper = getTimeHelper();
        TimeValue timeout = windowOpenOnStartupTimeout;

        Stopwatch stopwatch = helper.createStopwatch();

        while (!WorkbenchState.STARTUP.isProcessed() || timeout.duration() <= stopwatch.elapsed(timeout.unit())) {}

        if (WorkbenchState.STARTUP.isProcessed()) {
          final long time = helper.currentTime();

          execute(time, OPEN, workbench);
        }
      }
    });
  }

  public boolean preShutdown(final IWorkbench workbench, final boolean forced) {
    // ignore

    return true;
  }

  public void postShutdown(final IWorkbench workbench) {
    // ignore
  }
}
