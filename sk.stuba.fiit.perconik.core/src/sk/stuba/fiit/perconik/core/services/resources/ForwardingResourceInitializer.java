package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.ForwardingInitializer;

public abstract class ForwardingResourceInitializer extends ForwardingInitializer implements ResourceInitializer
{
	protected ForwardingResourceInitializer()
	{
	}

	@Override
	protected abstract ResourceInitializer delegate();
}
