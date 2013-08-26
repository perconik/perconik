package sk.stuba.fiit.perconik.core.services;

public abstract class AbstractProvider implements Provider
{
	protected AbstractProvider()
	{
	}
	
	@Override
	public final String toString()
	{
		return this.getName();
	}

	public final String getName()
	{
		return this.getClass().getName();
	}
}
