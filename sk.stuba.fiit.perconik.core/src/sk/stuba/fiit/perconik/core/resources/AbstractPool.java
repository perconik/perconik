package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

abstract class AbstractPool<L extends Listener> implements Pool<L>
{
	final Handler<L> handler;
	
	final Collection<L> listeners;
	
	AbstractPool(final Handler<L> handler, final Collection<L> implementation)
	{
		this.handler   = Preconditions.checkNotNull(handler);
		this.listeners = Preconditions.checkNotNull(implementation);
	}
	
	public final void add(final L listener)
	{
		if (!this.contains(listener))
		{
			this.handler.add(listener);
			this.listeners.add(listener);
		}
	}
	
	public final void remove(final L listener)
	{
		this.handler.remove(listener);
		this.listeners.remove(listener);
	}
}
