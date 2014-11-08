package sk.stuba.fiit.perconik.data.content;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

public interface AnyContent extends Content {
  public Map<String, Object> any();

  public void merge(Content content);

  public void merge(Map<String, Object> content);

  public void merge(Iterable<Entry<String, Object>> content);

  public void merge(Iterator<Entry<String, Object>> content);

  public void put(String key, @Nullable Object value);

  public Object get(String key);
}
