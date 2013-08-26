package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.ForwardingNameable;

public abstract class ForwardingProvider extends ForwardingNameable implements Provider
{
	protected ForwardingProvider()
	{
	}

	@Override
	protected abstract Provider delegate();
	
	public Provider parent()
	{
		return this.delegate().parent();
	}
}
