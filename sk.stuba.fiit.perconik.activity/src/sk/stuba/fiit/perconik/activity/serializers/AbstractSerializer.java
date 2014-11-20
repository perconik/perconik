package sk.stuba.fiit.perconik.activity.serializers;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.newStructuredContent;

public abstract class AbstractSerializer<T> implements Serializer<T> {
  protected AbstractSerializer() {}

  protected abstract void put(StructuredContent content, T object);

  public final StructuredContent serialize(@Nullable final T object) {
    if (object == null) {
      return null;
    }

    StructuredContent content = newStructuredContent();

    this.put(content, object);

    return content;
  }
}
