package sk.stuba.fiit.perconik.activity.listeners.ui.element;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PartSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.selection.StructuredSelectionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;

import static sk.stuba.fiit.perconik.activity.listeners.ui.element.ElementSelectionListener.Action.SELECT;
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
public final class ElementSelectionListener extends ActivityListener implements StructuredSelectionListener {
  public ElementSelectionListener() {}

  enum Action implements ActivityListener.Action {
    SELECT;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "element", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  Event build(final long time, final Action action, final IWorkbenchPart part) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("part"), this.execute(asDisplayTask(new PartSerializer(), part)));

    IWorkbenchPartSite site = part.getSite();

    if (site != null) {
      IWorkbenchPage page = site.getPage();
      IWorkbenchWindow window = page.getWorkbenchWindow();
      IWorkbench workbench = window.getWorkbench();

      data.put(key("part", "page"), identifyObject(page));
      data.put(key("part", "page", "window"), identifyObject(window));
      data.put(key("part", "page", "window", "workbench"), identifyObject(workbench));
    }

    return data;
  }

  Event build(final long time, final Action action, final IWorkbenchPart part, final IStructuredSelection selection) {
    Event data = this.build(time, action, part);

    data.merge(new StructuredSelectionSerializer(TREE).serialize(selection));

    return data;
  }

  void process(final long time, final Action action, final IWorkbenchPart part, final IStructuredSelection selection) {
    this.send(action.getPath(), this.build(time, action, part, selection));
  }

  void execute(final long time, final Action action, final IWorkbenchPart part, final IStructuredSelection selection) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, part, selection);
      }
    });
  }

  public void selectionChanged(final IWorkbenchPart part, final IStructuredSelection selection) {
    final long time = this.currentTime();

    this.execute(time, SELECT, part, selection);
  }
}
