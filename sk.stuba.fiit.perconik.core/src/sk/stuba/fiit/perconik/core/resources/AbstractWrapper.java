package sk.stuba.fiit.perconik.core.resources;

import com.google.common.base.Preconditions;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.Listener;

abstract class AbstractWrapper<L extends Listener> extends Adapter implements Wrapper<L>
{
	final L listener;
	
	AbstractWrapper(final L listener)
	{
		this.listener = Preconditions.checkNotNull(listener);
	}
	
	public final L forListener()
	{
		return this.listener;
	}
}
