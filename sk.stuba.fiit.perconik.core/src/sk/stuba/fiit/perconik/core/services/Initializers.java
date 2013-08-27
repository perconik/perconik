package sk.stuba.fiit.perconik.core.services;

public final class Initializers
{
	private Initializers()
	{
		throw new AssertionError();
	}
	
	public static final Runnable toRunnable(final Initializer initializer)
	{
		return new Runnable()
		{
			public final void run()
			{
				initializer.preInitialize();
				initializer.initialize();
				initializer.postInitialize();
			}
		};
	}
}
