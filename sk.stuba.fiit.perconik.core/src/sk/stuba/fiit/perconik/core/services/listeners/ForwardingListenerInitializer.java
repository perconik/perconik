package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.ForwardingInitializer;

public abstract class ForwardingListenerInitializer extends ForwardingInitializer implements ListenerInitializer
{
	protected ForwardingListenerInitializer()
	{
	}

	@Override
	protected abstract ListenerInitializer delegate();
}
