package sk.stuba.fiit.perconik.debug.services;

import sk.stuba.fiit.perconik.core.services.AbstractListenerService;
import sk.stuba.fiit.perconik.core.services.ListenerService;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class DebugListenerServiceWrapper extends AbstractListenerService
{
	DebugListenerServiceWrapper(final Builder builder)
	{
		super(builder);
	}
	
	private static final class Builder extends AbstractListenerService.Builder
	{
		public Builder()
		{
		}

		@Override
		public final DebugListenerServiceWrapper build()
		{
			return new DebugListenerServiceWrapper(this);
		}
	}
	
	private static final Builder builder()
	{
		return new Builder();
	}

	public static final DebugListenerServiceWrapper of(final ListenerService service)
	{
		return of(service, Debug.getDefaultConsole());
	}
	
	public static final DebugListenerServiceWrapper of(final ListenerService service, final PluginConsole console)
	{
		Builder builder = builder();
		
		builder.provider(DebugListenerProviderProxy.of(service.getListenerProvider(), console));
		builder.manager(DebugListenerManagerProxy.of(service.getListenerManager(), console));
		
		return builder.build();
	}
}
