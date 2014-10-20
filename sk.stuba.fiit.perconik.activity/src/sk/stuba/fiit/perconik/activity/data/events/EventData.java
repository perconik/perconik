package sk.stuba.fiit.perconik.activity.data.events;

import java.util.Set;

import sk.stuba.fiit.perconik.activity.data.base.AnnotableData;
import sk.stuba.fiit.perconik.activity.data.base.AnnotationData;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotations;

public class EventData extends AnnotableData {
  protected long timestamp;

  protected String action;

  protected Set<String> tags;

  public EventData() {}

  protected EventData(final String action) {
    if (action == null) {
      return;
    }

    this.setAnnotations(AnnotationData.of(Annotations.ofClass(this.getClass())));
  }

  public static EventData of(final String action) {
    return new EventData(action);
  }

  public void setTimestamp(final long timestamp) {
    this.timestamp = timestamp;
  }

  public void setAction(final String action) {
    this.action = action;
  }

  public void setTags(final Set<String> tags) {
    this.tags = tags;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public String getAction() {
    return this.action;
  }

  public Set<String> getTags() {
    return this.tags;
  }
}
