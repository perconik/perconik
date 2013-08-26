package sk.stuba.fiit.perconik.core.services;

public abstract class AbstractManager implements Manager
{
	protected AbstractManager()
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
