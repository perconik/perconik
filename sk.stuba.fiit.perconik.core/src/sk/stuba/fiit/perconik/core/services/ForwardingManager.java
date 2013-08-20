package sk.stuba.fiit.perconik.core.services;

public abstract class ForwardingManager extends ForwardingNameable implements Manager
{
	protected ForwardingManager()
	{
	}

	@Override
	protected abstract Manager delegate();
}
