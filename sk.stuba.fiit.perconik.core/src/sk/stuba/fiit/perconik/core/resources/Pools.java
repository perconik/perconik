package sk.stuba.fiit.perconik.core.resources;

import com.google.common.base.Preconditions;

class Pools
{
	private enum DefaultPoolFactory implements PoolFactory
	{
		INSTANCE;
		
		public final <T> Pool<T> create(final Handler<T> handler)
		{
			return Synchronized.pool(GenericPool.builder(handler).identity().hashSet().build());
		}
	}
	
	private static PoolFactory objectPoolFactory = DefaultPoolFactory.INSTANCE;
	
	private static PoolFactory listenerPoolFactory = DefaultPoolFactory.INSTANCE;
	
	private Pools()
	{
		throw new AssertionError();
	}
	
	static final void setObjectPoolFactory(final PoolFactory factory)
	{
		objectPoolFactory = Preconditions.checkNotNull(factory);
	}
	
	static final void setListenerPoolFactory(final PoolFactory factory)
	{
		listenerPoolFactory = Preconditions.checkNotNull(factory);
	}

	static final PoolFactory getObjectPoolFactory()
	{
		return objectPoolFactory;
	}
	
	static final PoolFactory getListenerPoolFactory()
	{
		return listenerPoolFactory;
	}
}
