package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.plugin.Activator;

/**
 * An abstract implementation of {@link Provider}
 * interface covering provider name and equivalence.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractProvider implements Provider
{
	/**
	 * Constructor for use by subclasses.
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}
		
		if (!(o instanceof Provider))
		{
			return false;
		}

		Provider other = (Provider) o;

		return this.getName().equals(other.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode()
	{
		return this.getName().hashCode();
	}
	
	/**
	 * Converts provider to string consisting of its name.
	 */
	@Override
	public final String toString()
	{
		return this.getName();
	}

	/**
	 * Returns provider name.
	 */
	public final String getName()
	{
		return this.getClass().getName();
	}
}
