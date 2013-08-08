package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import com.google.common.base.Preconditions;

abstract class AbstractPool<T> implements Pool<T>
{
	final Collection<T> objects;
	
	final Handler<T> handler;
	
	AbstractPool(final Collection<T> implementation, final Handler<T> handler)
	{
		this.objects = Preconditions.checkNotNull(implementation);
		this.handler = Preconditions.checkNotNull(handler);
	}
	
	public final void add(final T listener)
	{
		if (!this.contains(listener))
		{
			this.handler.register(listener);
			this.objects.add(listener);
		}
	}
	
	public final void remove(final T listener)
	{
		this.handler.unregister(listener);
		this.objects.remove(listener);
	}
}
