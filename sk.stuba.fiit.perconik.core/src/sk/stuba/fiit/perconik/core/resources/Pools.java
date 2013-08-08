package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

class Pools
{
	private enum DefaultPoolFactory implements ListenerPoolFactory
	{
		INSTANCE;
		
		public final <L extends Listener> Pool<L> create(final Handler<L> handler)
		{
			return Synchronized.pool(GenericPool.builder(handler).identity().hashSet().build());
		}
	}
	
	private static ListenerPoolFactory factory = DefaultPoolFactory.INSTANCE;
	
	private Pools()
	{
		throw new AssertionError();
	}

	static final void setListenerPoolFactory(final ListenerPoolFactory factory)
	{
		Pools.factory = Preconditions.checkNotNull(factory);
	}
	
	public static final ListenerPoolFactory getListenerPoolFactory()
	{
		return Pools.factory;
	}
}
