package sk.stuba.fiit.perconik.core;

import java.util.Set;
import com.google.common.collect.ImmutableSet;

public abstract class AbstractFilteringListener<T> extends Adapter implements FilteringListener<T>
{
	protected Set<T> types;
	
	protected AbstractFilteringListener(final Set<T> types)
	{
		this.types = ImmutableSet.copyOf(types);
	}
	
	public final Set<T> getEventTypes()
	{
		return this.types;
	}
}
