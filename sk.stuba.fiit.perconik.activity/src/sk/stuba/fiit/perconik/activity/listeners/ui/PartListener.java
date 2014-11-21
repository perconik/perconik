package sk.stuba.fiit.perconik.activity.listeners.ui;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PartReferenceSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;

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

  enum Action implements CommonEventListener.Action {
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

  static Event build(final long time, final Action action, final IWorkbenchPartReference reference) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("part"), new PartReferenceSerializer(TREE).serialize(reference));

    IWorkbenchPage page = reference.getPage();
    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    data.put(key("part", "page"), identifyObject(page));
    data.put(key("part", "page", "window"), identifyObject(window));
    data.put(key("part", "page", "window", "workbench"), identifyObject(workbench));

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchPartReference reference) {
    this.send(action.getPath(), build(time, action, reference));
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
