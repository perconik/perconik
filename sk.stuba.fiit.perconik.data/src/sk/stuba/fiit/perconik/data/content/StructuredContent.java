package sk.stuba.fiit.perconik.data.content;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

public interface StructuredContent extends Content {
  public static final String separator = ".";

  public Content flatten();

  public Content structure();

  public Content merge(Content content);

  public Content merge(Map<String, Object> content);

  public Content merge(Iterable<? extends Entry<String, Object>> content);

  public Content merge(Iterator<? extends Entry<String, Object>> content);

  public void put(String key, @Nullable Object value);

  public void put(Iterable<String> key, @Nullable Object value);

  public void put(Iterator<String> key, @Nullable Object value);

  public Object get(String key);

  public Object get(String key, String ... more);

  public Object get(Iterable<String> key);

  public Object get(Iterator<String> key);
}
