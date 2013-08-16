package sk.stuba.fiit.perconik.debug.services;

import sk.stuba.fiit.perconik.core.services.AbstractResourceService;
import sk.stuba.fiit.perconik.core.services.ResourceService;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class DebugResourceServiceWrapper extends AbstractResourceService
{
	DebugResourceServiceWrapper(final Builder builder)
	{
		super(builder);
	}
	
	private static final class Builder extends AbstractResourceService.Builder
	{
		public Builder()
		{
		}

		@Override
		public final DebugResourceServiceWrapper build()
		{
			return new DebugResourceServiceWrapper(this);
		}
	}
	
	private static final Builder builder()
	{
		return new Builder();
	}

	public static final DebugResourceServiceWrapper of(final ResourceService service)
	{
		return of(service, Debug.getDefaultConsole());
	}
	
	public static final DebugResourceServiceWrapper of(final ResourceService service, final DebugConsole console)
	{
		Builder builder = builder();
		
		builder.provider(DebugResourceProviderProxy.of(service.getResourceProvider(), console));
		builder.manager(DebugResourceManagerProxy.of(service.getResourceManager(), console));
		
		return builder.build();
	}
}
