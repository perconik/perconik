package sk.stuba.fiit.perconik.utilities;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import static com.google.common.collect.Maps.newHashMapWithExpectedSize;

/**
 * Static utility methods pertaining to {@code Map} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreMaps {
  private MoreMaps() {}

  public static <K, V> HashMap<Equivalence.Wrapper<K>, V> newHashMap(final Equivalence<? super K> equivalence, final Iterable<? extends Entry<? extends K, ? extends V>> entries) {
    HashMap<Equivalence.Wrapper<K>, V> map = newHashMapExpectedFor(entries);

    wrapAll(map, equivalence, entries);

    return map;
  }

  public static <K, V> HashMap<K, V> newHashMap(final Iterable<? extends Entry<Equivalence.Wrapper<? extends K>, ? extends V>> entries) {
    HashMap<K, V> map = newHashMapExpectedFor(entries);

    unwrapAll(map, entries);

    return map;
  }

  public static <K, V> HashMap<K, V> newHashMapExpectedFor(final Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return newHashMapWithExpectedSize(((Collection<?>) iterable).size());
    }

    return Maps.newHashMap();
  }

  public static <K, V> LinkedHashMap<Equivalence.Wrapper<K>, V> newLinkedHashMap(final Equivalence<? super K> equivalence, final Iterable<? extends Entry<? extends K, ? extends V>> entries) {
    LinkedHashMap<Equivalence.Wrapper<K>, V> map = Maps.newLinkedHashMap();

    wrapAll(map, equivalence, entries);

    return map;
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(final Iterable<? extends Entry<Equivalence.Wrapper<? extends K>, ? extends V>> entries) {
    LinkedHashMap<K, V> map = Maps.newLinkedHashMap();

    unwrapAll(map, entries);

    return map;
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

  public static <K, V> void wrapAll(final Map<Equivalence.Wrapper<K>, V> map, final Equivalence<? super K> equivalence, final Iterable<? extends Entry<? extends K, ? extends V>> entries) {
    for (Entry<? extends K, ? extends V> entry: entries) {
      map.put(equivalence.wrap((K) entry.getKey()), entry.getValue());
    }
  }

  public static <K, V> void unwrapAll(final Map<K, V> map, final Iterable<? extends Entry<Equivalence.Wrapper<? extends K>, ? extends V>> entries) {
    for (Entry<Equivalence.Wrapper<? extends K>, ? extends V> entry: entries) {
      map.put(entry.getKey().get(), entry.getValue());
    }
  }

  private enum ToKeyFunction implements Function<Entry<Object, Object>, Object> {
    INSTANCE;

    public Object apply(@Nonnull final Entry<Object, Object> entry) {
      return entry.getKey();
    }
  }

  private enum ToValueFunction implements Function<Entry<Object, Object>, Object> {
    INSTANCE;

    public Object apply(@Nonnull final Entry<Object, Object> entry) {
      return entry.getValue();
    }
  }

  public static <E extends Entry<? extends K, ?>, K> Function<E, K> toKeyFunction() {
    @SuppressWarnings("unchecked")
    Function<E, K> result = (Function<E, K>) ToKeyFunction.INSTANCE;

    return result;
  }

  public static <E extends Entry<?, ? extends V>, V> Function<E, V> toValueFunction() {
    @SuppressWarnings("unchecked")
    Function<E, V> result = (Function<E, V>) ToValueFunction.INSTANCE;

    return result;
  }
}
