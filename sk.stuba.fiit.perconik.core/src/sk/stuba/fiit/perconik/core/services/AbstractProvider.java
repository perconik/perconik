package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.plugin.Activator;

public abstract class AbstractProvider implements Provider
{
	protected AbstractProvider()
	{
	}

	protected final static void failure(final Throwable cause, final String format, final Object ... args)
	{
		failure(cause, String.format(format, args));
	}

	protected final static void failure(final Throwable cause, final String message)
	{
		Activator.getDefault().getConsole().error(message, cause);
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
