package sk.stuba.fiit.perconik.utilities;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newHashMapWithExpectedSize;

/**
 * Static utility methods pertaining to {@code Map} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreMaps {
  private MoreMaps() {}

  public static <K, V> HashMap<K, V> newHashMapExpectedFor(final Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return newHashMapWithExpectedSize(((Collection<?>) iterable).size());
    }

    return newHashMap();
  }

  private static <K, V> void copy(final Dictionary<? extends K, ? extends V> from, final Map<K, V> to) {
    Enumeration<? extends K> keys = from.keys();

    while (keys.hasMoreElements()) {
      K key = keys.nextElement();

      to.put(key, from.get(key));
    }
  }

  public static <K, V> Map<K, V> fromDictionary(final Dictionary<K, V> dictionary) {
    Map<K, V> map = newHashMapWithExpectedSize(dictionary.size());

    copy(dictionary, map);

    return map;
  }

  public static Map<String, Object> flatten(final Map<?, Object> map) {
    return flatten(map, Joiner.on("."));
  }

  public static Map<String, Object> flatten(final Map<?, Object> map, final Joiner joiner) {
    return flatten(map, joiner, Maps.<String, Object>newLinkedHashMap());
  }

  public static Map<String, Object> flatten(final Map<?, Object> map, final Joiner joiner, final Map<String, Object> result) {
    return flatten(map, joiner, result, (String) null);
  }

  @SuppressWarnings("unchecked")
  private static Map<String, Object> flatten(final Map<?, Object> map, final Joiner joiner, final Map<String, Object> result, final String prefix) {
    for (Entry<?, Object> entry: map.entrySet()) {
      String key = joiner.join(prefix, entry.getKey());
      Object value = entry.getValue();

      if (value instanceof Map) {
        flatten(((Map<?, Object>) value), joiner, result, key);
      } else {
        result.put(key, value);
      }
    }

    return result;
  }

  public static <K, V> void putAll(final Map<K, V> map, final Iterable<? extends Entry<? extends K, ? extends V>> entries) {
    for (Entry<? extends K, ? extends V> entry: entries) {
      map.put(entry.getKey(), entry.getValue());
    }
  }

  public static <K, V> void putAll(final Map<K, V> map, final Iterator<? extends Entry<? extends K, ? extends V>> entries) {
    while (entries.hasNext()) {
      Entry<? extends K, ? extends V> entry = entries.next();

      map.put(entry.getKey(), entry.getValue());
    }
  }
}
