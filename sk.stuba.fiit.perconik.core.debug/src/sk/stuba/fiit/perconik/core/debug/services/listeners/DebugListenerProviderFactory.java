package sk.stuba.fiit.perconik.core.debug.services.listeners;

import javax.annotation.Nonnull;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;

public final class DebugListenerProviderFactory implements ListenerProviderFactory
{
	public DebugListenerProviderFactory()
	{
	}

	public final ListenerProvider create(@Nonnull final ListenerProvider parent)
	{
		return DebugListenerProviders.create(parent);
	}
}
