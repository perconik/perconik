package sk.stuba.fiit.perconik.core.services;

public abstract class ForwardingProvider extends ForwardingNameable implements Provider
{
	protected ForwardingProvider()
	{
	}

	@Override
	protected abstract Provider delegate();
}
