package sk.stuba.fiit.perconik.core.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

abstract class AbstractHook<U, T extends Listener> extends Adapter implements Hook<U, T>
{
	final Set<U> objects;
	
	final T listener;
	
	AbstractHook(final Set<U> objects, final T listener)
	{
		this.objects  = Preconditions.checkNotNull(objects);
		this.listener = Preconditions.checkNotNull(listener);
	}
	
	// TODO
	final void addAll(final Set<U> objects)
	{
		for (U object: objects)
		{
			this.add(object);
		}
	}
	
	final void removeAll()
	{
		for (U object: this.objects)
		{
			this.remove(object);
		}
	}
	
	public final T forListener()
	{
		return this.listener;
	}
}
