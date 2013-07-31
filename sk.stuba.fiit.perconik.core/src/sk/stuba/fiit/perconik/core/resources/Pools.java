package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.Listener;
import com.google.common.base.Preconditions;

class Pools
{
	private enum DefaultPoolFactory implements PoolFactory
	{
		INSTANCE;
		
		public final <T extends Listener> Pool<T> create(final Handler<T> handler)
		{
			return Synchronized.pool(GenericPool.builder(handler).identity().hashSet().build());
		}
	}
	
	private static PoolFactory factory = DefaultPoolFactory.INSTANCE;
	
	private Pools()
	{
		throw new AssertionError();
	}

	static final void setPoolFactory(final PoolFactory factory)
	{
		Pools.factory = Preconditions.checkNotNull(factory);
	}
	
	static final PoolFactory getPoolFactory()
	{
		return Pools.factory;
	}
}
