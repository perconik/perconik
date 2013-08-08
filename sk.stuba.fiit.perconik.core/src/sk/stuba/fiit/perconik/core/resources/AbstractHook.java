package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

abstract class AbstractHook<T, L extends Listener> extends Adapter implements Hook<T, L>
{
	private final Pool<T> pool;
	
	AbstractHook(final Pool<T> pool)
	{
		this.pool = Preconditions.checkNotNull(pool);
	}
	
	public final void add(final T object)
	{
		this.pool.add(object);
	}

	final void addAll(final Collection<T> objects)
	{
		for (T object: objects)
		{
			this.add(object);
		}
	}

	public final void remove(final T object)
	{
		this.pool.remove(object);
	}
	
	final void removeAll(final Collection<T> objects)
	{
		for (T object: objects)
		{
			this.remove(object);
		}
	}
	
	public final Collection<T> toCollection()
	{
		return this.pool.toCollection();
	}
}
