package sk.stuba.fiit.perconik.core.services;

import java.util.Map;
import sk.stuba.fiit.perconik.core.listeners.DefaultListeners;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.MapMaker;

final class Internals
{
	private static final Map<Class<?>, Supplier<?>> suppliers;
	
	static
	{
		suppliers = new MapMaker().concurrencyLevel(1).makeMap();
		
		setApi(ResourceService.class, new Supplier<ResourceService>() {
			public final ResourceService get() {
				return DefaultResources.getDefaultResourceService();
			}
		});

		setApi(ListenerService.class, new Supplier<ListenerService>() {
			public final ListenerService get() {
				return DefaultListeners.getDefaultListenerService();
			}
		});
	}
	
	private Internals()
	{
		throw new AssertionError();
	}

	private static final class ImmutableReference<T> implements Supplier<T>
	{
		private final T object;
		
		ImmutableReference(final T object)
		{
			this.object = object;
		}

		public final T get()
		{
			return this.object;
		}
	}
	
	static final <T> void setApi(final Class<T> api, final T implementation)
	{
		Preconditions.checkNotNull(implementation);
		
		suppliers.put(api, new ImmutableReference<>(implementation));
	}
	
	static final <T> void setApi(final Class<T> api, final Supplier<T> supplier)
	{
		Preconditions.checkNotNull(supplier);
		
		suppliers.put(api, supplier);
	}
	
	static final <T> T getApi(final Class<T> api)
	{
		T implementation = api.cast(suppliers.get(api).get());
			
		if (implementation != null)
		{
			return implementation;
		}
		
		throw new UnsupportedOperationException("Unable to get implementation for " + api);
	}
}
