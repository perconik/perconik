package sk.stuba.fiit.perconik.core.resources;

import java.util.Map;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.resources.SelectionHandler.InternalSelectionHook;
import com.google.common.collect.Maps;

final class HookSupport<H extends Hook<?, T>, T extends Listener>
{
	private final Map<T, H> map;
	
	HookSupport()
	{
		this.map = Maps.newConcurrentMap();
	}
	
	final void hook(final Resource<? super H> resource, final T listener)
	{
		H hook = this.map.get(listener);
		
		if (hook == null)
		{
			// TODO edit
			hook = (H) new InternalSelectionHook((SelectionListener) listener);
			
			this.map.put(listener, hook);
		}
		
		resource.register(hook);
	}
	
	final void unhook(final Resource<? super H> resource, final T listener)
	{
		H hook = this.map.get(listener);
		
		if (hook != null)
		{
			resource.unregister(hook);
		}
	}
}
