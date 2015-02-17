package sk.stuba.fiit.perconik.utilities;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.base.Equivalence;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import static com.google.common.collect.Sets.newHashSetWithExpectedSize;
import static com.google.common.collect.Sets.newLinkedHashSetWithExpectedSize;

/**
 * Static utility methods pertaining to {@code Set} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreSets {
  private MoreSets() {}

  public static <E> HashSet<Equivalence.Wrapper<E>> newHashSet(final Equivalence<? super E> equivalence, final Iterable<? extends E> elements) {
    HashSet<Equivalence.Wrapper<E>> set = newHashSetExpectedFor(elements);

    wrapAll(set, equivalence, elements);

    return set;
  }

  public static <E> HashSet<E> newHashSet(final Iterable<Equivalence.Wrapper<? extends E>> elements) {
    HashSet<E> set = newHashSetExpectedFor(elements);

    unwrapAll(set, elements);

    return set;
  }

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

  public static <E> HashSet<E> newHashSetExpectedFor(final Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return newHashSetWithExpectedSize(((Collection<?>) iterable).size());
    }

    return Sets.newHashSet();
  }

  public static <E> LinkedHashSet<Equivalence.Wrapper<E>> newLinkedHashSet(final Equivalence<? super E> equivalence, final Iterable<? extends E> elements) {
    LinkedHashSet<Equivalence.Wrapper<E>> set = newLinkedHashSetExpectedFor(elements);

    wrapAll(set, equivalence, elements);

    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Iterable<Equivalence.Wrapper<? extends E>> elements) {
    LinkedHashSet<E> set = newLinkedHashSetExpectedFor(elements);

    unwrapAll(set, elements);

    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Iterable<? extends E> a, final Iterable<? extends E> b) {
    LinkedHashSet<E> set = Sets.newLinkedHashSet(a);

    Iterators.addAll(set, b.iterator());

    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Iterator<? extends E> a, final Iterator<? extends E> b) {
    LinkedHashSet<E> set = Sets.newLinkedHashSet();

    Iterators.addAll(set, a);
    Iterators.addAll(set, b);

    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSetExpectedFor(final Iterable<?> iterable) {
    if (iterable instanceof Collection) {
      return newLinkedHashSetWithExpectedSize(((Collection<?>) iterable).size());
    }

    return Sets.newLinkedHashSet();
  }

  public static <E extends Enum<E>> EnumSet<E> newEnumSet(final Iterable<E> elements) {
    return EnumSet.copyOf(MoreLists.toList(elements));
  }

  public static <E> void wrapAll(final Set<Equivalence.Wrapper<E>> set, final Equivalence<? super E> equivalence, final Iterable<? extends E> elements) {
    for (E element: elements) {
      set.add(equivalence.wrap(element));
    }
  }

  public static <E> void unwrapAll(final Set<E> set, final Iterable<Equivalence.Wrapper<? extends E>> elements) {
    for (Equivalence.Wrapper<? extends E> element: elements) {
      set.add(element.get());
    }
  }

  public static <E> Set<E> toSet(final Iterable<E> elements) {
    return elements instanceof Set ? (Set<E>) elements : Sets.newHashSet(elements);
  }

  public static <E> HashSet<E> toHashSet(final Iterable<E> elements) {
    return elements instanceof HashSet ? (HashSet<E>) elements : Sets.newHashSet(elements);
  }

  public static <E> LinkedHashSet<E> toLinkedHashSet(final Iterable<E> elements) {
    return elements instanceof LinkedHashSet ? (LinkedHashSet<E>) elements : Sets.newLinkedHashSet(elements);
  }

  public static <E extends Comparable<E>> TreeSet<E> toTreeSet(final Iterable<E> elements) {
    return elements instanceof TreeSet ? (TreeSet<E>) elements : Sets.newTreeSet(elements);
  }

  public static <E extends Enum<E>> EnumSet<E> toEnumSet(final Iterable<E> elements) {
    return elements instanceof EnumSet ? (EnumSet<E>) elements : newEnumSet(elements);
  }
}
