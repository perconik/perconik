package sk.stuba.fiit.perconik.utilities;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;

import static java.util.Arrays.asList;

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

  public static Map<String, Object> flatten(final Map<?, Object> map, final Joiner joiner, final Supplier<? extends Map<String, Object>> supplier) {
    return flatten(map, joiner, supplier.get());
  }

  @SuppressWarnings("unchecked")
  private static Map<String, Object> flatten(final Map<?, Object> map, final Joiner joiner, final Map<String, Object> result, final String prefix) {
    for (Entry<?, Object> entry: map.entrySet()) {
      String key = prefix != null ? joiner.join(prefix, entry.getKey()) : joiner.join(asList(entry.getKey()));
      Object value = entry.getValue();

      if (value instanceof Map) {
        flatten(((Map<?, Object>) value), joiner, result, key);
      } else {
        result.put(key, value);
      }
    }

    return result;
  }

  public static Map<String, Object> structure(final Map<?, ?> map) {
    return structure(map, Splitter.on(".").trimResults().omitEmptyStrings());
  }

  public static Map<String, Object> structure(final Map<?, ?> map, final Splitter splitter) {
    return structure(map, splitter, new Supplier<Map<String, Object>>() {
      public Map<String, Object> get() {
        return Maps.newLinkedHashMap();
      }
    });
  }

  public static Map<String, Object> structure(final Map<?, ?> map, final Splitter splitter, final Supplier<? extends Map<String, Object>> supplier) {
    Map<String, Object> result = supplier.get();

    for (Entry<?, ?> entry: map.entrySet()) {
      Object value = entry.getValue();

      if (value instanceof Map) {
        value = structure((Map<?, ?>) value, splitter, supplier);
      }

      List<String> keys = splitter.splitToList(entry.getKey().toString());

      int last = keys.size() - 1;

      Map<String, Object> submap;

      if (last == 0) {
        submap = result;
      } else {
        submap = substructure(result, keys.subList(0, last), supplier);
      }

      submap.put(keys.get(last), value);
    }

    return result;
  }

  @SuppressWarnings("unchecked")
  private static Map<String, Object> substructure(final Map<String, Object> map, final Iterable<String> keys, final Supplier<? extends Map<String, Object>> supplier) {
    Map<String, Object> submap = map;

    for (String key: keys) {
      Object value = submap.get(key);

      if (value instanceof Map) {
        submap = (Map<String, Object>) value;
      } else {
        Map<String, Object> link = supplier.get();

        submap.put(key, link);

        submap = link;
      }
    }

    return submap;
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
