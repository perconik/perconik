package sk.stuba.fiit.perconik.activity.listeners.ui;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.WorkbenchSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.data.events.Event;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static sk.stuba.fiit.perconik.activity.listeners.ui.WorkbenchListener.Action.SHUTDOWN;
import static sk.stuba.fiit.perconik.activity.listeners.ui.WorkbenchListener.Action.STARTUP;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.7.alpha")
public final class WorkbenchListener extends ActivityListener implements sk.stuba.fiit.perconik.core.listeners.WorkbenchListener {
  public WorkbenchListener() {}

  enum Action implements ActivityListener.Action {
    STARTUP,

    SHUTDOWN;

    private final String name;

    private final String path;

    private final AtomicBoolean done;

    private Action() {
      this.name = actionName("eclipse", "workbench", this);
      this.path = actionPath(this.name);

      this.done = new AtomicBoolean(false);
    }

    public boolean canProcess() {
      return this.done.compareAndSet(false, true);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }

    public boolean isProcessed() {
      return this.done.get();
    }
  }

  static Event build(final long time, final Action action, final IWorkbench workbench) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("workbench"), new WorkbenchSerializer(TREE).serialize(workbench));

    return data;
  }

  void process(final long time, final Action action, final IWorkbench workbench) {
    this.send(action.getPath(), build(time, action, workbench));
  }

  void execute(final long time, final Action action, final IWorkbench workbench) {
    // execute in display task as soon as possible,
    // otherwise some widgets may be disposed

    this.execute(DisplayTask.of(new Runnable() {
      public void run() {
        process(time, action, workbench);
      }
    }));
  }

  public void postStartup(final IWorkbench workbench) {
    final long time = this.currentTime();

    if (STARTUP.canProcess()) {
      this.execute(time, STARTUP, workbench);
    }
  }

  public boolean preShutdown(final IWorkbench workbench, final boolean forced) {
    // ignore

    return true;
  }

  public void postShutdown(final IWorkbench workbench) {
    final long time = this.currentTime();

    if (SHUTDOWN.canProcess()) {
      this.execute(time, SHUTDOWN, workbench);
    }
  }

  public static boolean isStartupProcessed() {
    return STARTUP.isProcessed();
  }

  public static boolean isShutdownProcessed() {
    return SHUTDOWN.isProcessed();
  }
}
