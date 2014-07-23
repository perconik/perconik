package sk.stuba.fiit.perconik.utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;

/**
 * Static utility methods pertaining to {@code List} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreLists {
  private MoreLists() {
    throw new AssertionError();
  }

  public static final List<?> wrap(Object object) {
    if (object instanceof List) {
      return (List<?>) object;
    }

    return asList(MoreArrays.wrap(object));
  }

  public static final <E> List<E> toList(Iterable<E> elements) {
    return elements instanceof List ? (List<E>) elements : newArrayList(elements);
  }

  public static final <E> ArrayList<E> toArrayList(Iterable<E> elements) {
    return elements instanceof ArrayList ? (ArrayList<E>) elements : newArrayList(elements);
  }

  public static final <E> LinkedList<E> toLinkedList(Iterable<E> elements) {
    return elements instanceof LinkedList ? (LinkedList<E>) elements : newLinkedList(elements);
  }
}
