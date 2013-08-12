package sk.stuba.fiit.perconik.core;

import java.util.Set;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.ResourceService;
import com.google.common.base.Preconditions;

public final class Resources
{
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
		ServiceAccessor.INSTANCE.set(service);
	}
	
	public static final ResourceService getResourceService()
	{
		return ServiceAccessor.INSTANCE.get();
	}
	
	public static final <L extends Listener> void register(Class<L> type, Resource<L> resource)
	{
		getResourceService().register(type, resource);
	}

	public static final <L extends Listener> void unregister(Class<L> type, Resource<L> resource)
	{
		getResourceService().unregister(type, resource);
	}

	public static final Set<Resource<?>> registered()
	{
		return getResourceService().registered();
	}

	public static final <L extends Listener> Set<Resource<? super L>> registerable(Class<L> type)
	{
		return getResourceService().registerable(type);
	}
	
	public static final <L extends Listener> Set<Resource<? extends L>> assignable(Class<L> type)
	{
		return getResourceService().assignable(type);
	}
}
