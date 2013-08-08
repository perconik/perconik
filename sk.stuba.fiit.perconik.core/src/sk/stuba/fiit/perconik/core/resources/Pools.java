package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

class Pools
{
	private enum DefaultPoolFactory implements GenericPoolFactory
	{
		INSTANCE;
		
		public final <T extends Listener> Pool<T> create(final Handler<T> handler)
		{
			return Synchronized.pool(GenericPool.builder(handler).identity().hashSet().build());
		}
	}
	
	private static GenericPoolFactory factory = DefaultPoolFactory.INSTANCE;
	
	private Pools()
	{
		throw new AssertionError();
	}

	static final void setPoolFactory(final GenericPoolFactory factory)
	{
		Pools.factory = Preconditions.checkNotNull(factory);
	}
	
	public static final GenericPoolFactory getPoolFactory()
	{
		return Pools.factory;
	}
}
