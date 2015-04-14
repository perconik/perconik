package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ui.PartSerializer;
import sk.stuba.fiit.perconik.activity.serializers.ui.text.LineRegionSerializer;
import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializers.asDisplayTask;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractTextListener extends ActivityListener {
  AbstractTextListener() {}

  final Event build(final long time, final Action action, final IWorkbenchPart part) {
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

  final Event build(final long time, final Action action, final IWorkbenchPart part, final LineRegion region) {
    Event data = this.build(time, action, part);

    data.put(key("region"), new LineRegionSerializer().serialize(region));

    return data;
  }

  final void process(final long time, final Action action, final IWorkbenchPart part, final LineRegion region) {
    this.send(action.getPath(), this.build(time, action, part, region));
  }
}
