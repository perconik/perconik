package sk.stuba.fiit.perconik.activity.listeners.ui;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PerspectiveDescriptorSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.DEACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.SAVE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
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

  enum Action implements CommonEventListener.Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE,

    SAVE;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "perspective", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  private static void putPageIdentity(final StructuredContent content, final IWorkbenchPage page) {
    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    content.put(key("page"), identifyObject(page));
    content.put(key("page", "window"), identifyObject(window));
    content.put(key("page", "window", "workbench"), identifyObject(workbench));

    IPerspectiveDescriptor descriptor = page.getPerspective();

    content.put(key("page", "perspective"), identifyObject(descriptor));
  }

  static Event build(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("perspective"), new PerspectiveDescriptorSerializer().serialize(descriptor));

    putPageIdentity(data, page);

    return data;
  }

  static Event build(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    Event data = LocalEvent.of(time, action.getName());

    PerspectiveDescriptorSerializer serializer = new PerspectiveDescriptorSerializer();

    data.put(key("before"), serializer.serialize(before));
    data.put(key("after"), serializer.serialize(after));

    putPageIdentity(data, page);

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    this.send(action.getPath(), build(time, action, page, descriptor));
  }

  void process(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    this.send(action.getPath(), build(time, action, page, before, after));
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
    final long time = this.currentTime();

    this.execute(time, OPEN, page, descriptor);
  }

  public void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    final long time = this.currentTime();

    this.execute(time, CLOSE, page, descriptor);
  }

  public void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    final long time = this.currentTime();

    this.execute(time, ACTIVATE, page, descriptor);
  }

  public void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    final long time = this.currentTime();

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
    final long time = this.currentTime();

    this.execute(time, SAVE, page, before, after);
  }
}
