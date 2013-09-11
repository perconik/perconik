package sk.stuba.fiit.perconik.utilities;

import java.util.HashSet;
import java.util.Iterator;
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
	
	public static <E> HashSet<E> newHashSet(Iterable<? extends E> a, Iterable<? extends E> b)
	{
		HashSet<E> set = Sets.newHashSet(a);
		
		Iterators.addAll(set, b.iterator());
		
		return set;
	}

	public static <E> HashSet<E> newHashSet(Iterator<? extends E> a, Iterator<? extends E> b)
	{
		HashSet<E> set = Sets.newHashSet();

		Iterators.addAll(set, a);
		Iterators.addAll(set, b);
		
		return set;
	}
}
