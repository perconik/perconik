package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import sk.stuba.fiit.perconik.utilities.MoreLists;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.Accessors;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static com.google.common.collect.Sets.newTreeSet;

public final class Annotations {
  private Annotations() {}

  public static List<Annotation> ofClass(final Class<?> type) {
    return ofElement(type);
  }

  public static List<Annotation> ofClasses(final Iterable<? extends Class<?>> types) {
    return ofElements(types);
  }

  public static List<Annotation> ofElement(final AnnotatedElement element) {
    return ImmutableList.copyOf(element.getAnnotations());
  }

  public static List<Annotation> ofElements(final Iterable<? extends AnnotatedElement> elements) {
    ImmutableList.Builder<Annotation> annotations = ImmutableList.builder();

    for (AnnotatedElement element: elements) {
      annotations.addAll(ofElement(element));
    }

    return annotations.build();
  }

  public static Annotable asAnnotable(final Collection<Annotation> annotations) {
    return new EnumeratedAnnotable(annotations.iterator());
  }

  public static Annotable asAnnotable(final Iterable<Annotation> annotations) {
    return new EnumeratedAnnotable(annotations.iterator());
  }

  public static Annotable asAnnotable(final Iterator<Annotation> annotations) {
    return new EnumeratedAnnotable(annotations);
  }

  public static Map<String, String> toData(final Annotation annotation) {
    Map<String, String> data = newLinkedHashMap();

    for (Entry<String, Object> entry: toElements(annotation).entrySet()) {
      String key = keyToString(entry.getKey());
      String value = valueToString(entry.getValue());

      data.put(key, value == null || value.isEmpty() ? null : value);
    }

    return data;
  }

  public static Map<String, Object> toElements(final Annotation annotation) {
    Map<String, Object> elements = newLinkedHashMap();

    for (Method method: annotation.annotationType().getDeclaredMethods()) {
      String name = method.getName();

      Accessor<Object> accessor = Accessors.ofInstanceMethod(annotation, Object.class, name).get();

      elements.put(name, accessor.get());
    }

    return elements;
  }

  public static String toString(final Annotation annotation) {
    Class<? extends Annotation> type = annotation.annotationType();

    Map<String, String> data = Maps.filterValues(toData(annotation), notNull());

    StringBuilder builder = new StringBuilder(keyToString(type.getSimpleName()));

    if (data.size() == 1 && data.containsKey("value")) {
      builder.append(" (").append(data.get("value")).append(")");
    } else if (data.size() != 0) {
      builder.append(" (");

      Joiner.on(", ").withKeyValueSeparator(": ").appendTo(builder, data);

      builder.append(")");
    }

    return builder.toString();
  }

  public static String toString(final Iterable<Annotation> annotations) {
    Set<String> values = newTreeSet();

    for (Annotation annotation: annotations) {
      values.add(toString(annotation));
    }

    return Joiner.on(", ").join(values);
  }

  private static String keyToString(final Object object) {
    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, object.toString());
  }

  private static String valueToString(final Object object) {
    Set<String> elements = newTreeSet();

    for (Object element: MoreLists.wrap(object)) {
      if (element instanceof Class) {
        elements.add(((Class<?>) element).getCanonicalName());

        continue;
      }

      String value = Objects.toString(element, "");

      if (value.isEmpty()) {
        continue;
      }

      elements.add(value);
    }

    return Joiner.on(", ").join(elements);
  }
}
