package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.listeners.PerspectiveListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.PerspectiveListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.PerspectiveListener.Action.DEACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.PerspectiveListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.listeners.PerspectiveListener.Action.SAVE;
import static sk.stuba.fiit.perconik.activity.listeners.Serializations.identify;
import static sk.stuba.fiit.perconik.activity.listeners.Serializations.serializePerspective;
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
public final class PerspectiveListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.PerspectiveListener {
  public PerspectiveListener() {}

  enum Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE,

    SAVE;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "perspective", this);
      this.path = actionPath(this.name);
    }
  }

  private static void setPage(final StructuredContent content, final IWorkbenchPage page) {
    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    content.put(key("page"), identify(page));
    content.put(key("page", "window"), identify(window));
    content.put(key("page", "window", "workbench"), identify(workbench));

    IPerspectiveDescriptor descriptor = page.getPerspective();

    content.put(key("page", "perspective"), identify(descriptor));
  }

  static Event build(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    Event event = LocalEvent.of(time, action.name);

    event.put(key("perspective"), serializePerspective(descriptor));

    setPage(event, page);

    return event;
  }

  static Event build(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    Event event = LocalEvent.of(time, action.name);

    event.put(key("before"), serializePerspective(before));
    event.put(key("after"), serializePerspective(after));

    setPage(event, page);

    return event;
  }

  void process(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.send(action.path, build(time, action, page, descriptor));
  }

  void process(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    this.send(action.path, build(time, action, page, before, after));
  }

  void execute(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, page, descriptor);
      }
    });
  }

  void execute(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, page, before, after);
      }
    });
  }

  public void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    final long time = currentTime();

    this.execute(time, OPEN, page, descriptor);
  }

  public void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    final long time = currentTime();

    this.execute(time, CLOSE, page, descriptor);
  }

  public void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    final long time = currentTime();

    this.execute(time, ACTIVATE, page, descriptor);
  }

  public void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    final long time = currentTime();

    this.execute(time, DEACTIVATE, page, descriptor);
  }

  public void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    // ignore
  }

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final String change) {
    // ignore
  }

  public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final IWorkbenchPartReference reference, final String change) {
    // ignore
  }

  public void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    final long time = currentTime();

    this.execute(time, SAVE, page, before, after);
  }
}
