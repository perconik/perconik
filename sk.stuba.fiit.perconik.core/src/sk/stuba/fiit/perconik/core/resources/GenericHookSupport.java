package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

final class GenericHookSupport<H extends Hook<T, L>, T, L extends Listener> extends AbstractHookSupport<H, T, L>
{
	private final HookFactory<T, L> factory;
	
	GenericHookSupport(final HookFactory<T, L> factory)
	{
		this.factory = Preconditions.checkNotNull(factory);
	}
	
	static final <H extends Hook<T, L>, T, L extends Listener> GenericHookSupport<H, T, L> using(final HookFactory<T, L> factory)
	{
		return new GenericHookSupport<>(factory);
	}

	public final Hook<T, L> create(final L listener)
	{
		return this.factory.create(listener);
	}
}
