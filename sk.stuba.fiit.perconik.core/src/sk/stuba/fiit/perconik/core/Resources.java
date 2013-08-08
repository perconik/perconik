package sk.stuba.fiit.perconik.core;

import java.util.Set;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
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
	
	public static final <L extends Listener> void register(Class<L> type, Resource<L> resource)
	{
		getResourceService().register(type, resource);
	}

	public static final <L extends Listener> void unregister(Class<L> type, Resource<L> resource)
	{
		service.get().unregister(type, resource);
	}

	public static final Set<Resource<?>> registered()
	{
		return service.get().registered();
	}

	public static final <L extends Listener> Set<Resource<? super L>> registerable(Class<L> type)
	{
		return service.get().registerable(type);
	}
	
	public static final <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type)
	{
		return service.get().assignable(type);
	}
}
