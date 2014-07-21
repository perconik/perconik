package sk.stuba.fiit.perconik.core.resources;

import com.google.common.base.Preconditions;

import sk.stuba.fiit.perconik.core.Listener;

final class StandardHookSupport<H extends Hook<T, L>, T, L extends Listener> extends AbstractHookSupport<H, T, L>
{
	private final HookFactory<T, L> factory;
	
	StandardHookSupport(final HookFactory<T, L> factory)
	{
		this.factory = Preconditions.checkNotNull(factory);
	}
	
	static final <H extends Hook<T, L>, T, L extends Listener> StandardHookSupport<H, T, L> using(final HookFactory<T, L> factory)
	{
		return new StandardHookSupport<>(factory);
	}

	public final Hook<T, L> create(final L listener)
	{
		return this.factory.create(listener);
	}
}
