package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.services.Services;

final class StandardListenerInitializer implements ListenerInitializer
{
	StandardListenerInitializer()
	{
	}
	
	public final void run()
	{
		ListenerProvider provider = Services.getListenerService().getListenerProvider();
		
		for (Class<? extends Listener> type: provider.classes())
		{
			Listeners.register(provider.forClass(type));
		}
	}
}
