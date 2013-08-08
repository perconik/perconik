package sk.stuba.fiit.perconik.core.resources;

import com.google.common.base.Preconditions;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

abstract class InternalHook<T, L extends Listener> extends AbstractHook<T, L>
{
	private final String name;
	
	private final InternalHandler<T, L> handler;
	
	InternalHook(final InternalHandler<T, L> handler)
	{
		super(pool(handler));
		
		this.name    = name(handler);
		this.handler = Preconditions.checkNotNull(handler);
	}

	static abstract class InternalHandler<T, L extends Listener> implements Handler<T>
	{
		final L listener;
		
		InternalHandler(final L listener)
		{
			this.listener = Preconditions.checkNotNull(listener);
		}
	}
	
	private static final <T> Pool<T> pool(final InternalHandler<T, ?> handler)
	{
		return Pools.getObjectPoolFactory().create(handler);
	}
	
	private static final String name(final InternalHandler<?, ?> handler)
	{
		SmartStringBuilder name = new SmartStringBuilder();
		
		name.append(handler.listener.getClass().getCanonicalName());
		
		if (name.isEmpty())
		{
			name.append(handler.listener.getClass().getName());
		}
		
		return name.replaceLast(".", ".Internal").replaceLast("Listener", "Hook").toString();
	}

	@Override
	public final void preRegister()
	{
		this.preRegisterInternal();
		this.addAll(this.toCollection());
	}

	@Override
	public final void postRegister()
	{
		this.postRegisterInternal();
	}

	@Override
	public final void preUnregister()
	{
		this.preUnregisterInternal();
	}

	@Override
	public final void postUnregister()
	{
		this.removeAll(this.toCollection());
		this.postUnregisterInternal();
	}
	
	void preRegisterInternal()
	{
	}

	void postRegisterInternal()
	{
	}

	void preUnregisterInternal()
	{
	}

	void postUnregisterInternal()
	{
	}
	
	public final L forListener()
	{
		return this.handler.listener;
	}

	@Override
	public final String toString()
	{
		return this.getName();
	}

	public final String getName()
	{
		return this.name;
	}
}
