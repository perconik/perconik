package sk.stuba.fiit.perconik.core.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.listeners.Listener;
import com.google.common.base.Preconditions;

public final class Resources
{
	static final ServiceAccessor service = ServiceAccessor.INSTANCE;
	
	private Resources()
	{
		throw new AssertionError();
	}

	private static enum ServiceAccessor
	{
		INSTANCE;
		
		private ResourceService service;
		
		final synchronized void set(final ResourceService service)
		{
			this.service = Preconditions.checkNotNull(service);
		}
		
		final synchronized ResourceService get()
		{
			if (this.service != null)
			{
				return this.service;
			}
			
			return DefaultResources.getDefaultResourceService();
		}
	}
	
	public static final void setResourceService(final ResourceService service)
	{
		Resources.service.set(service);
	}
	
	public static final ResourceService getResourceService()
	{
		return Resources.service.get();
	}
	
	public static final <T extends Listener> void register(Class<T> type, Resource<T> resource)
	{
		getResourceService().register(type, resource);
	}

	public static final <T extends Listener> void unregister(Class<T> type, Resource<T> resource)
	{
		service.get().unregister(type, resource);
	}

	public static final Set<Resource<?>> registered()
	{
		return service.get().registered();
	}

	public static final <T extends Listener> Set<Resource<? super T>> registerable(Class<T> type)
	{
		return service.get().registerable(type);
	}
	
	public static final <T extends Listener> Set<Resource<? extends T>> assignable(Class<T> type)
	{
		return service.get().assignable(type);
	}
}
