package sk.stuba.fiit.perconik.activity.listeners.ui;

import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.WorkbenchSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static sk.stuba.fiit.perconik.activity.listeners.ui.WorkbenchListener.Action.SHUTDOWN;
import static sk.stuba.fiit.perconik.activity.listeners.ui.WorkbenchListener.Action.STARTUP;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
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
    Event data = LocalEvent.of(time, action.name);

    data.put(key("workbench"), new WorkbenchSerializer(TREE).serialize(workbench));

    return data;
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
