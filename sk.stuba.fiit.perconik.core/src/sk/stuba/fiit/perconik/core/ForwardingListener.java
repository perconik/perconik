package sk.stuba.fiit.perconik.core;

public abstract class ForwardingListener extends ForwardingRegistrable implements Listener
{
	protected ForwardingListener()
	{
	}
	
	@Override
	protected abstract Listener delegate();
}
