package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;

import static sk.stuba.fiit.perconik.activity.listeners.Serializations.identify;
import static sk.stuba.fiit.perconik.activity.listeners.Serializations.serializeWindowTree;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionName;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionPath;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.currentTime;
import static sk.stuba.fiit.perconik.activity.listeners.WindowListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.WindowListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.WindowListener.Action.DEACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.WindowListener.Action.OPEN;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class WindowListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.WindowListener {
  public WindowListener() {}

  enum Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "window", this);
      this.path = actionPath(this.name);
    }
  }

  static Event build(final long time, final Action action, final IWorkbenchWindow window) {
    Event event = LocalEvent.of(time, action.name);

    event.put(key("window"), serializeWindowTree(window));

    IWorkbench workbench = window.getWorkbench();

    event.put(key("window", "workbench"), identify(workbench));

    return event;
  }

  void process(final long time, final Action action, final IWorkbenchWindow window) {
    this.send(action.path, build(time, action, window));
  }

  void execute(final long time, final Action action, final IWorkbenchWindow window) {
    this.execute(DisplayTask.of(new Runnable() {
      public void run() {
        process(time, action, window);
      }
    }));
  }

  public void windowOpened(final IWorkbenchWindow window) {
    final long time = currentTime();

    this.execute(time, OPEN, window);
  }

  public void windowClosed(final IWorkbenchWindow window) {
    final long time = currentTime();

    this.execute(time, CLOSE, window);
  }

  public void windowActivated(final IWorkbenchWindow window) {
    final long time = currentTime();

    this.execute(time, ACTIVATE, window);
  }

  public void windowDeactivated(final IWorkbenchWindow window) {
    final long time = currentTime();

    this.execute(time, DEACTIVATE, window);
  }
}
