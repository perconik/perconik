package sk.stuba.fiit.perconik.activity.listeners.ui;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PartReferenceSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.data.events.Event;

import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.BRING_TO_TOP;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.CHANGE_INPUT;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.DEACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.HIDE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.OPEN;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PartListener.Action.SHOW;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializers.asDisplayTask;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCase;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.4.alpha")
public final class PartListener extends ActivityListener implements sk.stuba.fiit.perconik.core.listeners.PartListener {
  public PartListener() {}

  enum Action implements ActivityListener.Action {
    OPEN,

    CLOSE,

    ACTIVATE,

    DEACTIVATE,

    SHOW,

    HIDE,

    BRING_TO_TOP,

    CHANGE_INPUT;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "part", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  Event build(final long time, final Action action, final IWorkbenchPartReference reference) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("reference"), this.execute(asDisplayTask(new PartReferenceSerializer(TREE), reference)));

    IWorkbenchPage page = reference.getPage();
    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    data.put(key("reference", "page"), identifyObject(page));
    data.put(key("reference", "page", "window"), identifyObject(window));
    data.put(key("reference", "page", "window", "workbench"), identifyObject(workbench));

    IPerspectiveDescriptor descriptor = page.getPerspective();

    data.put(key("reference", "page", "perspective"), identifyObject(descriptor));

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchPartReference reference) {
    this.send(action.getPath(), this.build(time, action, reference));
  }

  void execute(final long time, final Action action, final IWorkbenchPartReference reference) {
    IWorkbench workbench = reference.getPage().getWorkbenchWindow().getWorkbench();

    if (workbench.isClosing() && (action == CLOSE || action == DEACTIVATE || action == HIDE)) {
      if (this.log.isEnabled()) {
        this.log.print("%s: workbench is closing, %1$s %s event not processed", "part", toLowerCase(action));
      }

      return;
    }

    this.execute(new Runnable() {
      public void run() {
        process(time, action, reference);
      }
    });
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, OPEN, reference);
  }

  public void partClosed(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, CLOSE, reference);
  }

  public void partActivated(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, ACTIVATE, reference);
  }

  public void partDeactivated(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, DEACTIVATE, reference);
  }

  public void partVisible(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, SHOW, reference);
  }

  public void partHidden(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, HIDE, reference);
  }

  public void partBroughtToTop(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, BRING_TO_TOP, reference);
  }

  public void partInputChanged(final IWorkbenchPartReference reference) {
    final long time = this.currentTime();

    this.execute(time, CHANGE_INPUT, reference);
  }
}
