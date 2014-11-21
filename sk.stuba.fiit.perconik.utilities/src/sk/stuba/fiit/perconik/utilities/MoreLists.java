package sk.stuba.fiit.perconik.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;
import static com.google.common.collect.Lists.newArrayListWithExpectedSize;
import static com.google.common.collect.Lists.newLinkedList;

/**
 * Static utility methods pertaining to {@code List} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreLists {
  private MoreLists() {}

  public static <E> ArrayList<E> newArrayListSuitableFor(final Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return newArrayListWithCapacity(((Collection<?>) iterable).size());
    }

    return newArrayList();
  }

  public static <E> ArrayList<E> newArrayListExpectedFor(final Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return newArrayListWithExpectedSize(((Collection<?>) iterable).size());
    }

    return newArrayList();
  }

  public static List<?> wrap(final Object object) {
    if (object instanceof List) {
      return (List<?>) object;
    }

    return asList(MoreArrays.wrap(object));
  }

  public static <E> List<E> toList(final Iterable<E> elements) {
    return elements instanceof List ? (List<E>) elements : newArrayList(elements);
  }

  public static <E> ArrayList<E> toArrayList(final Iterable<E> elements) {
    return elements instanceof ArrayList ? (ArrayList<E>) elements : newArrayList(elements);
  }

  public static <E> LinkedList<E> toLinkedList(final Iterable<E> elements) {
    return elements instanceof LinkedList ? (LinkedList<E>) elements : newLinkedList(elements);
  }
}
