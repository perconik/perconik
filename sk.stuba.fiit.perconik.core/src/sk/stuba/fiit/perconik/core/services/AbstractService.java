package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Service;

public abstract class AbstractService extends com.google.common.util.concurrent.AbstractService implements Service
{
	protected AbstractService()
	{
	}
	
	@Override
	public final String toString()
	{
		return this.getName() + " [" + this.state().toString().toLowerCase() + "]";
	}

	public final String getName()
	{
		return this.getClass().getName();
	}
}
