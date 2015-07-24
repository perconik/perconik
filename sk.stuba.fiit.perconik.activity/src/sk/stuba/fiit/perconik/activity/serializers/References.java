package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Equivalence;
import com.google.common.base.Predicate;
import com.google.common.collect.TreeTraverser;

import sk.stuba.fiit.perconik.data.content.AnyContent;

import static java.lang.Integer.toHexString;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Strings.padStart;
import static com.google.common.collect.Maps.immutableEntry;
import static com.google.common.collect.Maps.newLinkedHashMap;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.MoreLists.newArrayListSuitableFor;

public final class References {
  private References() {}

  public static Map<String, Object> extractObjects(final AnyContent content, final String prefix, final Iterable<String> keys, final Predicate<Object> filter) {
    return extractObjects(content, prefix, keys, filter, Equivalence.equals());
  }

  public static Map<String, Object> extractObjects(final AnyContent content, final String prefix, final Iterable<String> keys, final Predicate<Object> filter, final Equivalence<Object> equivalence) {
    checkNotNull(prefix);
    checkNotNull(keys);
    checkNotNull(filter);
    checkNotNull(equivalence);

    Map<String, Object> objects = newLinkedHashMap();

    for (Entry<Object, Object> entry: new AnyContentTraverser().preOrderTraversal(immutableEntry((Object) null, (Object) content))) {
      Object value = entry.getValue();

      if (value instanceof AnyContent) {
        value = ((AnyContent) value).any();
      }

      if (!(value instanceof Map)) {
        continue;
      }

      @SuppressWarnings("unchecked")
      Map<String, String> container = (Map<String, String>) value;

      for (String key: keys) {
        Object object = container.get(key);

        if (filter.apply(object)) {
          String hash = padStart(toHexString(equivalence.hash(object)), 8, '0');

          objects.put(hash, object);
          container.put(key, prefix + "@" + hash);
        }
      }
    }

    return objects;
  }

  private static final class AnyContentTraverser extends TreeTraverser<Entry<Object, Object>> {
    AnyContentTraverser() {}

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Entry<Object, Object>> children(final Entry<Object, Object> entry) {
      Object value = entry.getValue();

      if (value instanceof AnyContent) {
        value = ((AnyContent) value).any();
      }

      if (value instanceof Map) {
        return ((Map<Object, Object>) value).entrySet();
      }

      if (value instanceof Collection) {
        List<Entry<Object, Object>> children = newArrayListSuitableFor((Collection<?>) value);

        for (Object element: (Collection<?>) value) {
          children.add(immutableEntry((Object) null, element));
        }

        return children;
      }

      return emptyList();
    }
  }

  public static void referenceObjects(final AnyContent content, final String field, final Iterable<String> keys, final Predicate<Object> filter) {
    referenceObjects(content, field, keys, filter, Equivalence.equals());
  }

  public static void referenceObjects(final AnyContent content, final String field, final Iterable<String> keys, final Predicate<Object> filter, final Equivalence<Object> equivalence) {
    String key = key("internals", field);

    content.put(key, extractObjects(content, key, keys, filter, equivalence));
  }

  public static void referenceStrings(final AnyContent content) {
    referenceObjects(content, "strings", asList("description", "string", "text"), instanceOf(String.class));
  }
}
