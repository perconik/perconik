package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.collect.Lists;

final class StandardResource<L extends Listener> extends AbstractResource<L>
{
	final Pool<L> pool;
	
	StandardResource(final Pool<L> pool)
	{
		super(pool.toString().replace("Pool", "Resource"));
		
		this.pool = pool;
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
				filtered.add(type.cast(listener));
			}
		}
		
		return filtered;
	}
	
	public final boolean registered(final Listener listener)
	{
		return this.pool.contains(listener);
	}
}
