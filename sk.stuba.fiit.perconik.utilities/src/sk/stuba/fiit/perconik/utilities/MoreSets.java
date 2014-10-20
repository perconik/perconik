package sk.stuba.fiit.perconik.utilities;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static com.google.common.collect.Sets.newTreeSet;

/**
 * Static utility methods pertaining to {@code Set} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreSets {
  private MoreSets() {}

  public static <E> HashSet<E> newHashSet(final Iterable<? extends E> a, final Iterable<? extends E> b) {
    HashSet<E> set = Sets.newHashSet(a);

    Iterators.addAll(set, b.iterator());

    return set;
  }

  public static <E> HashSet<E> newHashSet(final Iterator<? extends E> a, final Iterator<? extends E> b) {
    HashSet<E> set = Sets.newHashSet();

    Iterators.addAll(set, a);
    Iterators.addAll(set, b);

    return set;
  }

  public static <E extends Enum<E>> EnumSet<E> newEnumSet(final Iterable<E> elements) {
    return EnumSet.copyOf(MoreLists.toList(elements));
  }

  public static <E> Set<E> toSet(final Iterable<E> elements) {
    return elements instanceof Set ? (Set<E>) elements : Sets.newHashSet(elements);
  }

  public static <E> HashSet<E> toHashSet(final Iterable<E> elements) {
    return elements instanceof HashSet ? (HashSet<E>) elements : Sets.newHashSet(elements);
  }

  public static <E> LinkedHashSet<E> toLinkedHashSet(final Iterable<E> elements) {
    return elements instanceof LinkedHashSet ? (LinkedHashSet<E>) elements : newLinkedHashSet(elements);
  }

  public static <E extends Comparable<E>> TreeSet<E> toTreeSet(final Iterable<E> elements) {
    return elements instanceof TreeSet ? (TreeSet<E>) elements : newTreeSet(elements);
  }

  public static <E extends Enum<E>> EnumSet<E> toEnumSet(final Iterable<E> elements) {
    return elements instanceof EnumSet ? (EnumSet<E>) elements : newEnumSet(elements);
  }
}
