package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

abstract class AbstractResource<T extends Listener> implements Resource<T>
{
	private final Pool<T> pool;
	
	AbstractResource(final Pool<T> pool)
	{
		this.pool = Preconditions.checkNotNull(pool);
	}
	
	public final void register(final T listener)
	{
		listener.preRegister();
		
		this.pool.add(listener);
		
		listener.postRegister();
	}
	
	public final void unregister(final T listener)
	{
		listener.preUnregister();
		
		this.pool.remove(listener);
		
		listener.postUnregister();
	}
	
	public final void unregisterAll(final Class<? extends Listener> type)
	{
		for (T listener: this.pool.toCollection())
		{
			if (type.isInstance(listener))
			{
				this.unregister(listener);
			}
		}
	}
	
	public final <U extends Listener> Collection<U> registered(final Class<U> type)
	{
		Collection<T> listeners = this.pool.toCollection();
		Collection<U> filtered  = Lists.newArrayListWithCapacity(listeners.size());
		
		for (T listener: listeners)
		{
			if (type.isInstance(listener))
			{
				@SuppressWarnings("unchecked")
				U casted = (U) listener;
				
				filtered.add(casted);
			}
		}
		
		return filtered;
	}
}
