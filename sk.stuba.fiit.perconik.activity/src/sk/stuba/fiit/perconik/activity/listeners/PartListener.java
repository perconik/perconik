package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.core.annotations.Version;

import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.BRING_TO_TOP;
import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.CHANGE_INPUT;
import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.DEACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.HIDE;
import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.listeners.PartListener.Action.SHOW;
import static sk.stuba.fiit.perconik.activity.listeners.Serializations.identify;
import static sk.stuba.fiit.perconik.activity.listeners.Serializations.serializePartReference;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionName;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.actionPath;
import static sk.stuba.fiit.perconik.activity.listeners.Utilities.currentTime;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class PartListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.PartListener {
  public PartListener() {}

  enum Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE,

    SHOW,

    HIDE,

    BRING_TO_TOP,

    CHANGE_INPUT;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "part", this);
      this.path = actionPath(this.name);
    }
  }

  static Event build(final long time, final Action action, final IWorkbenchPartReference reference) {
    Event event = LocalEvent.of(time, action.name);

    event.put(key("part"), serializePartReference(reference));

    IWorkbenchPage page = reference.getPage();
    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    event.put(key("part", "page"), identify(page));
    event.put(key("part", "page", "window"), identify(window));
    event.put(key("part", "page", "window", "workbench"), identify(workbench));

    return event;
  }

  void process(final long time, final Action action, final IWorkbenchPartReference reference) {
    this.send(action.path, build(time, action, reference));
  }

  void execute(final long time, final Action action, final IWorkbenchPartReference reference) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, reference);
      }
    });
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, OPEN, reference);
  }

  public void partClosed(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, CLOSE, reference);
  }

  public void partActivated(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, ACTIVATE, reference);
  }

  public void partDeactivated(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, DEACTIVATE, reference);
  }

  public void partVisible(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, SHOW, reference);
  }

  public void partHidden(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, HIDE, reference);
  }

  public void partBroughtToTop(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, BRING_TO_TOP, reference);
  }

  public void partInputChanged(final IWorkbenchPartReference reference) {
    final long time = currentTime();

    this.execute(time, CHANGE_INPUT, reference);
  }
}
