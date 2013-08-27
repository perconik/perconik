package sk.stuba.fiit.perconik.core.services;

public abstract class AbstractInitializer implements Initializer
{
	protected AbstractInitializer()
	{
	}

	public void preInitialize()
	{
	}

	public void postInitialize()
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
