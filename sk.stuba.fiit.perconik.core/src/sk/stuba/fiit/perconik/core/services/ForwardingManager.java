package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.ForwardingNameable;

public abstract class ForwardingManager extends ForwardingNameable implements Manager
{
	protected ForwardingManager()
	{
	}

	@Override
	protected abstract Manager delegate();
}
