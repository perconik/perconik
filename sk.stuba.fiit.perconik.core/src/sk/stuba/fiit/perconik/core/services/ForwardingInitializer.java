package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.ForwardingNameable;

public abstract class ForwardingInitializer extends ForwardingNameable implements Initializer
{
	protected ForwardingInitializer()
	{
	}

	@Override
	protected abstract Initializer delegate();

	public void preInitialize()
	{
		this.delegate().preInitialize();
	}

	public void initialize()
	{
		this.delegate().initialize();
	}

	public void postInitialize()
	{
		this.delegate().postInitialize();
	}
}
