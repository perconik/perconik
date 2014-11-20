package sk.stuba.fiit.perconik.activity.serializers;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.data.content.Content;

public interface Serializer<T> {
  public Content serialize(@Nullable T object);
}
