package sk.stuba.fiit.perconik.core.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.FilteringListener;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstant;
import sk.stuba.fiit.perconik.utilities.constant.IntegralConstantSupport;
import com.google.common.base.Preconditions;

final class Handlers
{
	private Handlers()
	{
		throw new AssertionError();
	}
	
	private static final class SafeHandler<T> implements Handler<T>
	{
		private final Handler<T> handler;
		
		private final Class<T> type;
		
		public SafeHandler(final Handler<T> handler, final Class<T> type)
		{
			this.handler = Preconditions.checkNotNull(handler);
			this.type    = Preconditions.checkNotNull(type);
		}

		private final T check(final T object)
		{
			return this.type.cast(Preconditions.checkNotNull(object));
		}
		
		public final void register(final T object)
		{
			this.handler.register(check(object));
		}

		public final void unregister(final T object)
		{
			this.handler.unregister(check(object));
		}

		@Override
		public final String toString()
		{
			return this.handler.toString();
		}
	}
	
	static final <E extends Enum<E> & IntegralConstant> int mask(final FilteringListener<E> listener)
	{
		Set<E> types = listener.getEventTypes();
		
		Preconditions.checkState(types != null && !types.isEmpty());
		
		return IntegralConstantSupport.constantsAsInteger(types);
	}
	
	static final <T> Handler<T> safe(final Handler<T> handler, final Class<T> type)
	{
		return new SafeHandler<>(handler, type);
	}
}
