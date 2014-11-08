package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static sk.stuba.fiit.perconik.activity.listeners.Serializations.serializeWorkbenchTree;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionName;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionPath;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.currentTime;
import static sk.stuba.fiit.perconik.activity.listeners.WorkbenchListener.Action.SHUTDOWN;
import static sk.stuba.fiit.perconik.activity.listeners.WorkbenchListener.Action.STARTUP;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForWorkbench;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class WorkbenchListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.WorkbenchListener {
  public WorkbenchListener() {}

  enum Action {
    STARTUP,

    SHUTDOWN;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "workbench", this);
      this.path = actionPath(this.name);
    }
  }

  static Event build(final long time, final Action action, final IWorkbench workbench) {
    Event event = LocalEvent.of(time, action.name);

    event.put(key("workbench"), serializeWorkbenchTree(workbench));

    return event;
  }

  void process(final long time, final Action action, final IWorkbench workbench) {
    this.send(action.path, build(time, action, workbench));
  }

  void execute(final long time, final Action action, final IWorkbench workbench) {
    this.execute(DisplayTask.of(new Runnable() {
      public void run() {
        process(time, action, workbench);
      }
    }));
  }

  @Override
  public void postRegister() {
    final long time = currentTime();

    this.execute(time, STARTUP, waitForWorkbench());
  }

  public boolean preShutdown(final IWorkbench workbench, final boolean forced) {
    final long time = currentTime();

    this.execute(time, SHUTDOWN, workbench);

    return true;
  }

  public void postShutdown(final IWorkbench workbench) {}
}
