package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

abstract class AbstractResource<L extends Listener> implements Resource<L>
{
	private final Pool<L> pool;
	
	AbstractResource(final Pool<L> pool)
	{
		this.pool = Preconditions.checkNotNull(pool);
	}
	
	public final void register(final L listener)
	{
		listener.preRegister();
		
		this.pool.add(listener);
		
		listener.postRegister();
	}
	
	public final void unregister(final L listener)
	{
		listener.preUnregister();
		
		this.pool.remove(listener);
		
		listener.postUnregister();
	}
	
	public final void unregisterAll(final Class<? extends Listener> type)
	{
		for (L listener: this.pool.toCollection())
		{
			if (type.isInstance(listener))
			{
				this.unregister(listener);
			}
		}
	}
	
	public final <U extends Listener> Collection<U> registered(final Class<U> type)
	{
		Collection<L> listeners = this.pool.toCollection();
		Collection<U> filtered  = Lists.newArrayListWithCapacity(listeners.size());
		
		for (L listener: listeners)
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
