package sk.stuba.fiit.perconik.activity.events;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import sk.stuba.fiit.perconik.activity.data.AnnotableData;
import sk.stuba.fiit.perconik.data.events.Event;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotations;

import static java.util.Collections.EMPTY_SET;

import static sk.stuba.fiit.perconik.activity.data.DataCollections.toAnnotationData;

public class BaseEvent extends AnnotableData implements Event {
  protected long timestamp;

  protected String action;

  protected Set<String> tags;

  public BaseEvent() {}

  protected BaseEvent(final long timestamp) {
    List<Annotation> annotations = Annotations.ofClass(this.getClass());

    this.setAnnotations(toAnnotationData(annotations));
    this.setTimestamp(timestamp);
    this.setTags(EMPTY_SET);
  }

  protected BaseEvent(final long timestamp, final String action) {
    this(timestamp);

    this.setAction(action);
  }

  public static BaseEvent of(final long timestamp) {
    return new BaseEvent(timestamp);
  }

  public static BaseEvent of(final long timestamp, final String action) {
    return new BaseEvent(timestamp, action);
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
