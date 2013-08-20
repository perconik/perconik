package sk.stuba.fiit.perconik.core;

import com.google.common.collect.ForwardingObject;

public abstract class ForwardingRegistrable extends ForwardingObject implements Registrable
{
	protected ForwardingRegistrable()
	{
	}
	
	@Override
	protected abstract Registrable delegate();

	public void preRegister()
	{
		this.delegate().preRegister();
	}

	public void postRegister()
	{
		this.delegate().postRegister();
	}

	public void preUnregister()
	{
		this.delegate().preUnregister();
	}

	public void postUnregister()
	{
		this.delegate().postUnregister();
	}
}
