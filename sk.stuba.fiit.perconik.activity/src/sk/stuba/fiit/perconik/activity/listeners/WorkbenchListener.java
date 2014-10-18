package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbench;

import sk.stuba.fiit.perconik.activity.data.events.EventData;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

import static sk.stuba.fiit.perconik.activity.listeners.Utilities.currentTime;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
@Version("0.0.1")
public final class WorkbenchListener extends Listener implements sk.stuba.fiit.perconik.core.listeners.WorkbenchListener {
  public WorkbenchListener() {}

  public enum Action {
    STARTUP,

    SHUTDOWN;

    final String identifier;

    private Action() {
      this.identifier = "eclipse.workbench." + this.name().toLowerCase();
    }
  }

  final EventData build(final long time, final Action action) {
    EventData data = new EventData();

    data.setTimestamp(time);
    data.setAction(action.identifier);

    // TODO
    //    data.put("core", new StandardCoreProbe().core());
    //    data.put("platform", new StandardPlatformProbe().platform());
    //    data.put("system", new StandardSystemProbe().system());

    return data;
  }

  @Override
  public final void postRegister() {
    final long time = currentTime();

    //Activator.waitForExtensions(); // TODO causes deadlock

    execute(new Runnable() {
      public final void run() {
        persist("eclipse/workbench/startup", build(time, Action.STARTUP));
      }
    });
  }

  public final boolean preShutdown(final IWorkbench workbench, final boolean forced) {
    final long time = currentTime();

    execute(new Runnable() {
      public final void run() {
        persist("workbench/shutdown", build(time, Action.SHUTDOWN));
      }
    });

    return true;
  }

  public final void postShutdown(final IWorkbench workbench) {}
}
