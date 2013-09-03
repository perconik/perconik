package sk.stuba.fiit.perconik.debug.services.listeners;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;

public final class DebugListenerProviderFactory implements ListenerProviderFactory
{
	public DebugListenerProviderFactory()
	{
	}

	public final ListenerProvider create(final ListenerProvider parent)
	{
		return DebugListenerProviders.create(parent);
	}
}
