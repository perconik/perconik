package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static com.google.common.collect.Maps.newLinkedHashMap;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newEmptyListSuitableFor;

public abstract class AbstractMultiSerializer<T> extends AbstractSerializer<T> implements MultiSerializer<T> {
  protected AbstractMultiSerializer() {}

  public final List<StructuredContent> serialize(final Iterable<? extends T> objects) {
    List<StructuredContent> contents = newEmptyListSuitableFor(objects);

    for (T object: objects) {
      contents.add(this.serialize(object));
    }

    return contents;
  }

  public final List<StructuredContent> serialize(final Iterator<? extends T> objects) {
    List<StructuredContent> contents = newEmptyListSuitableFor(objects);

    while (objects.hasNext()) {
      contents.add(this.serialize(objects.next()));
    }

    return contents;
  }

  public final Map<String, ? extends Content> serialize(final Map<?, ? extends T> objects) {
    Map<String, StructuredContent> contents = newLinkedHashMap();

    for (Entry<?, ? extends T> entry: objects.entrySet()) {
      contents.put(entry.getKey().toString(), this.serialize(entry.getValue()));
    }

    return contents;
  }
}
