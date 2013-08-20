package sk.stuba.fiit.perconik.core;

import com.google.common.collect.ForwardingObject;

public abstract class ForwardingNameable extends ForwardingObject implements Nameable
{
	protected ForwardingNameable()
	{
	}

	@Override
	protected abstract Nameable delegate();

	public String getName()
	{
		return this.delegate().getName();
	}
}
