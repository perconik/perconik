package sk.stuba.fiit.perconik.activity.data;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

public interface StructuredContent extends Content {
  public static final String separator = ".";

  public Map<String, Object> flatten();

  public void set(String key, @Nullable Object value);

  public void set(Iterable<String> key, @Nullable Object value);

  public void set(Iterator<String> key, @Nullable Object value);

  public Object get(String key);

  public Object get(String key, String ... more);

  public Object get(Iterable<String> key);

  public Object get(Iterator<String> key);
}
