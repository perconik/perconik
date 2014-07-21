package sk.stuba.fiit.perconik.core;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * An abstract implementation of {@link FilteringListener}
 * holding acceptable event types in an immutable set.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractFilteringListener<T> extends Adapter implements FilteringListener<T>
{
	/**
	 * Set of acceptable event types.
	 */
	protected Set<T> types;
	
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractFilteringListener(final Set<T> types)
	{
		this.types = ImmutableSet.copyOf(types);
	}
	
	public final Set<T> getEventTypes()
	{
		return this.types;
	}
}
