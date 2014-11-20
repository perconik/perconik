package sk.stuba.fiit.perconik.activity.listeners.resource;

import org.eclipse.core.resources.IResourceChangeEvent;

import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractResourceListener extends CommonEventListener implements ResourceListener {
  AbstractResourceListener() {}

  static final void put(final StructuredContent content, final ResourceEventVisit visit) {
    ResourceEventInput input = visit.input;

    content.put(key("source"), identifyObject(input.source));

    content.put(key("type"), input.type.toString().toLowerCase());

    if (input.build.isPresent()) {
      content.put(key("build", "kind"), input.build.get().toString().toLowerCase());
    }

    content.put(key("visit", "initial", "delta"), identifyObject(input.delta.orNull()));
    content.put(key("visit", "initial", "resource"), identifyObject(input.resource.orNull()));

    content.put(key("visit", "current", "delta"), identifyObject(visit.delta.orNull()));
    content.put(key("visit", "current", "resource"), identifyObject(visit.resource.orNull()));
  }

  abstract void resolve(final long time, final IResourceChangeEvent event);

  public final void resourceChanged(final IResourceChangeEvent event) {
    final long time = currentTime();

    this.execute(new Runnable() {
      public void run() {
        resolve(time, event);
      }
    });
  }
}
