package sk.stuba.fiit.perconik.core.listeners;

import java.util.Set;
import com.google.common.collect.ImmutableSet;
import sk.stuba.fiit.perconik.core.adapters.Adapter;

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
