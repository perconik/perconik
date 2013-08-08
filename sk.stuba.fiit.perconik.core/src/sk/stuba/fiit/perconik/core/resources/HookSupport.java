package sk.stuba.fiit.perconik.core.resources;

import java.util.Map;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

final class HookSupport<H extends Hook<?, T>, T extends Listener>
{
	private final HookFactory<H, T> factory;
	
	private final Map<T, H> hooks;
	
	HookSupport(final HookFactory<H, T> factory)
	{
		this.factory = Preconditions.checkNotNull(factory);
		this.hooks   = Maps.newHashMap();
	}
	
	final void hook(final Resource<? super H> resource, final T listener)
	{
		H hook = this.hooks.get(listener);
		
		if (hook == null)
		{
			hook = this.factory.create(listener);
			
			this.hooks.put(listener, hook);
		}
		
		resource.register(hook);
	}
	
	final void unhook(final Resource<? super H> resource, final T listener)
	{
		H hook = this.hooks.get(listener);
		
		if (hook != null)
		{
			resource.unregister(hook);
		}
	}
}
