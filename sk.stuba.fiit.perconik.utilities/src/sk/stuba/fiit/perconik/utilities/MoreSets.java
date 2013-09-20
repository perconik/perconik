package sk.stuba.fiit.perconik.utilities;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * Static utility methods pertaining to {@code Set} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreSets
{
	private MoreSets()
	{
		throw new AssertionError();
	}
	
	public static final <E> HashSet<E> newHashSet(Iterable<? extends E> a, Iterable<? extends E> b)
	{
		HashSet<E> set = Sets.newHashSet(a);
		
		Iterators.addAll(set, b.iterator());
		
		return set;
	}

	public static final <E> HashSet<E> newHashSet(Iterator<? extends E> a, Iterator<? extends E> b)
	{
		HashSet<E> set = Sets.newHashSet();

		Iterators.addAll(set, a);
		Iterators.addAll(set, b);
		
		return set;
	}
	
	public static final <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> elements)
	{
		return EnumSet.copyOf(MoreLists.toList(elements));
	}
	
	public static final <E> Set<E> toSet(Iterable<E> elements)
	{
		return elements instanceof Set ? (Set<E>) elements : Sets.newHashSet(elements);
	}
	
	public static final <E> HashSet<E> toHashSet(Iterable<E> elements)
	{
		return elements instanceof HashSet ? (HashSet<E>) elements : Sets.newHashSet(elements);
	}
	
	public static final <E> LinkedHashSet<E> toLinkedHashSet(Iterable<E> elements)
	{
		return elements instanceof LinkedHashSet ? (LinkedHashSet<E>) elements : Sets.newLinkedHashSet(elements);
	}
	
	public static final <E extends Comparable<E>> TreeSet<E> toTreeSet(Iterable<E> elements)
	{
		return elements instanceof TreeSet ? (TreeSet<E>) elements : Sets.newTreeSet(elements);
	}
	
	public static final <E extends Enum<E>> EnumSet<E> toEnumSet(Iterable<E> elements)
	{
		return elements instanceof EnumSet ? (EnumSet<E>) elements : newEnumSet(elements);
	}
}
