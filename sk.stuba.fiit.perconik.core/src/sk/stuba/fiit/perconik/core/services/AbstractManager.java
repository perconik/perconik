package sk.stuba.fiit.perconik.core.services;

import javax.annotation.Nullable;

/**
 * An abstract implementation of {@link Manager}
 * interface covering manager name and equivalence.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractManager implements Manager
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractManager()
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
		
		if (!(o instanceof Manager))
		{
			return false;
		}

		Manager other = (Manager) o;

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
	 * Converts provider to manager consisting of its name.
	 */
	@Override
	public final String toString()
	{
		return this.getName();
	}

	/**
	 * Returns manager name.
	 */
	public final String getName()
	{
		return this.getClass().getName();
	}
}
