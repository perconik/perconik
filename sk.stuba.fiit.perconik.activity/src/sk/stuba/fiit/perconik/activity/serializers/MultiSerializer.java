package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Iterator;
import java.util.Map;

import sk.stuba.fiit.perconik.data.content.Content;

public interface MultiSerializer<T> extends Serializer<T> {
  public Iterable<? extends Content> serialize(Iterable<? extends T> objects);

  public Iterable<? extends Content> serialize(Iterator<? extends T> objects);

  public Map<String, ? extends Content> serialize(Map<?, ? extends T> objects);
}
