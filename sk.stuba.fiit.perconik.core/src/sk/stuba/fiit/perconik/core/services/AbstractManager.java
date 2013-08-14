package sk.stuba.fiit.perconik.core.services;

abstract class AbstractManager
{
	AbstractManager()
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
