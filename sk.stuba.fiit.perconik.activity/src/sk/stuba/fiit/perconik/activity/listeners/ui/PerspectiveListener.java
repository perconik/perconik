package sk.stuba.fiit.perconik.activity.listeners.ui;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PageSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.PerspectiveDescriptorSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.DEACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PerspectiveListener.Action.SAVE;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializers.asDisplayTask;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.3.alpha")
public final class PerspectiveListener extends ActivityListener implements sk.stuba.fiit.perconik.core.listeners.PerspectiveListener {
  // TODO document lifecycle: click eclipse start, click open perspective B, deactivate(A),
  //      activate(B), open(B), click close B, deactivate(B), activate(A), close(B)

  // TODO SAVE does not work, even for debug listener

  public PerspectiveListener() {}

  enum Action implements ActivityListener.Action {
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

  private void put(final StructuredContent content, final IWorkbenchPage page) {
    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    content.put(key("page"), this.execute(asDisplayTask(new PageSerializer(TREE), page)));
    content.put(key("page", "window"), identifyObject(window));
    content.put(key("page", "window", "workbench"), identifyObject(workbench));

    IPerspectiveDescriptor descriptor = page.getPerspective();

    content.put(key("page", "perspective"), identifyObject(descriptor));
  }

  Event build(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor descriptor) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("perspective"), new PerspectiveDescriptorSerializer().serialize(descriptor));

    this.put(data, page);

    return data;
  }

  Event build(final long time, final Action action, final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after) {
    Event data = LocalEvent.of(time, action.getName());

    PerspectiveDescriptorSerializer serializer = new PerspectiveDescriptorSerializer();

    data.put(key("perspective", "before"), serializer.serialize(before));
    data.put(key("perspective", "after"), serializer.serialize(after));

    this.put(data, page);

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
