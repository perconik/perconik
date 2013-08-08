package sk.stuba.fiit.perconik.core.resources;

import java.util.ArrayList;
import java.util.Collection;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

abstract class AbstractHook<U, T extends Listener> extends Adapter implements Hook<U, T>
{
	final Collection<U> objects;
	
	final T listener;
	
	AbstractHook(final Collection<U> implementation, final T listener)
	{
		this.objects  = Preconditions.checkNotNull(implementation);
		this.listener = Preconditions.checkNotNull(listener);
	}
	
	final void addAll(final Collection<U> objects)
	{
		for (U object: objects)
		{
			this.add(object);
		}
	}
	
	final void removeAll(final Collection<U> objects)
	{
		for (U object: objects)
		{
			this.remove(object);
		}
	}
	
	public final Collection<U> objects()
	{
		return new ArrayList<>(this.objects);
	}
	
	public final T forListener()
	{
		return this.listener;
	}
}
