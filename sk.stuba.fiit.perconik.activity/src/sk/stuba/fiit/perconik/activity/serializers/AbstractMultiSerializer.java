package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Iterator;
import java.util.List;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

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
}
