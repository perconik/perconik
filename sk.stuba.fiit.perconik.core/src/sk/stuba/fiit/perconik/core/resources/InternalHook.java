package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

abstract class InternalHook<T, L extends Listener> extends AbstractHook<T, L>
{
	private final InternalHandler<T, L> handler;
	
	InternalHook(final InternalHandler<T, L> handler)
	{
		super(Pools.getObjectPoolFactory().create(handler));
		
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
		String name = this.handler.getClass().getCanonicalName();
		
		if (name.isEmpty())
		{
			name = this.handler.getClass().getName();
		}
		
		return name.replace("Handler", "Hook") + " for " + this.handler.listener;
	}
}
