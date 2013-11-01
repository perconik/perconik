package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;

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
