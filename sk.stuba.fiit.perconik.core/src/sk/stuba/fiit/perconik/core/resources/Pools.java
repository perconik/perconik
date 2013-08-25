package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
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
	
	private static final class SafePool<T> implements Pool<T>
	{
		private final Pool<T> pool;
		
		private final Class<T> type;
		
		SafePool(final Pool<T> pool, final Class<T> type)
		{
			this.pool = Preconditions.checkNotNull(pool);
			this.type = Preconditions.checkNotNull(type);
		}

		private final T check(final T object)
		{
			return this.type.cast(Preconditions.checkNotNull(object));
		}
		
		public final boolean contains(final T object)
		{
			return this.pool.contains(object);
		}

		public final void add(final T object)
		{
			this.pool.add(this.check(object));
		}

		public final void remove(final T object)
		{
			this.pool.remove(this.check(object));
		}

		public final Collection<T> toCollection()
		{
			return this.pool.toCollection();
		}

		@Override
		public final String toString()
		{
			return this.pool.toString();
		}
	}
	
	static final <T> Pool<T> safe(final Pool<T> pool, final Class<T> type)
	{
		return new SafePool<>(pool, type);
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
