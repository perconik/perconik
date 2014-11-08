package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.core.annotations.Version;

import static sk.stuba.fiit.perconik.activity.listeners.PageListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.PageListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.PageListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.listeners.Serializations.identify;
import static sk.stuba.fiit.perconik.activity.listeners.Serializations.serializePageTree;
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
public final class PageListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.PageListener {
  public PageListener() {}

  enum Action {
    OPEN,

    CLOSE,

    ACTIVATE;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "page", this);
      this.path = actionPath(this.name);
    }
  }

  static Event build(final long time, final Action action, final IWorkbenchPage page) {
    Event event = LocalEvent.of(time, action.name);

    event.put(key("page"), serializePageTree(page));

    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    event.put(key("page"), identify(window));
    event.put(key("page", "window"), identify(window));
    event.put(key("page", "window", "workbench"), identify(workbench));

    return event;
  }

  void process(final long time, final Action action, final IWorkbenchPage page) {
    this.send(action.path, build(time, action, page));
  }

  void execute(final long time, final Action action, final IWorkbenchPage page) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, page);
      }
    });
  }

  public void pageOpened(final IWorkbenchPage page) {
    final long time = currentTime();

    this.execute(time, OPEN, page);
  }

  public void pageClosed(final IWorkbenchPage page) {
    final long time = currentTime();

    this.execute(time, CLOSE, page);
  }

  public void pageActivated(final IWorkbenchPage page) {
    final long time = currentTime();

    this.execute(time, ACTIVATE, page);
  }
}
