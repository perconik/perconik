package sk.stuba.fiit.perconik.listeners;

import java.util.Collection;
import com.google.common.base.Preconditions;

abstract class AbstractPool<T extends Listener> implements Pool<T>
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
			this.handler.add(listener);
			this.listeners.add(listener);
		}
	}
	
	public final void remove(final T listener)
	{
		this.handler.remove(listener);
		this.listeners.remove(listener);
	}
}
