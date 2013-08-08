package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import com.google.common.base.Preconditions;

abstract class AbstractPool<T> implements Pool<T>
{
	final Handler<T> handler;
	
	final Collection<T> listeners;
	
	AbstractPool(final Handler<T> handler, final Collection<T> implementation)
	{
		this.handler   = Preconditions.checkNotNull(handler);
		this.listeners = Preconditions.checkNotNull(implementation);
	}
	
	public final void add(final T listener)
	{
		if (!this.contains(listener))
		{
			this.handler.register(listener);
			this.listeners.add(listener);
		}
	}
	
	public final void remove(final T listener)
	{
		this.handler.unregister(listener);
		this.listeners.remove(listener);
	}
}
