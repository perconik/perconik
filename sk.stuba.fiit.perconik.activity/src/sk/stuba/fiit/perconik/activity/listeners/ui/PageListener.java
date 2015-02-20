package sk.stuba.fiit.perconik.activity.listeners.ui;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PageSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;

import static sk.stuba.fiit.perconik.activity.listeners.ui.PageListener.Action.ACTIVATE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PageListener.Action.CLOSE;
import static sk.stuba.fiit.perconik.activity.listeners.ui.PageListener.Action.OPEN;
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
public final class PageListener extends CommonEventListener implements sk.stuba.fiit.perconik.core.listeners.PageListener {
  public PageListener() {}

  enum Action implements CommonEventListener.Action {
    OPEN,

    CLOSE,

    ACTIVATE;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "page", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final IWorkbenchPage page) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("page"), new PageSerializer(TREE).serialize(page));

    IWorkbenchWindow window = page.getWorkbenchWindow();
    IWorkbench workbench = window.getWorkbench();

    data.put(key("page", "window"), identifyObject(window));
    data.put(key("page", "window", "workbench"), identifyObject(workbench));

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchPage page) {
    this.send(action.getPath(), build(time, action, page));
  }

  void execute(final long time, final Action action, final IWorkbenchPage page) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, page);
      }
    });
  }

  public void pageOpened(final IWorkbenchPage page) {
    final long time = this.currentTime();

    this.execute(time, OPEN, page);
  }

  public void pageClosed(final IWorkbenchPage page) {
    final long time = this.currentTime();

    this.execute(time, CLOSE, page);
  }

  public void pageActivated(final IWorkbenchPage page) {
    final long time = this.currentTime();

    this.execute(time, ACTIVATE, page);
  }
}
