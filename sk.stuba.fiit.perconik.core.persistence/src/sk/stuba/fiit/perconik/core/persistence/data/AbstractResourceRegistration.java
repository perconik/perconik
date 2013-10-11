package sk.stuba.fiit.perconik.core.persistence.data;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.persistence.ResourceRegistration;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotables;

/**
 * An abstract implementation of the {@link ResourceRegistration} interface.
 * Implemented predicates like the current registration status are obtained
 * directly from the core using the underlying resource or resource's data.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractResourceRegistration extends AbstractAnnotableRegistration implements ResourceRegistration
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractResourceRegistration()
	{
	}

	/**
	 * Returns the backing annotable delegate instance.
	 */
	@Override
	protected final Annotable delegate()
	{
		return Annotables.fromAnnotations(Utilities.collect(this.getResource()));
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

		if (!(o instanceof ResourceRegistration))
		{
			return false;
		}

		ResourceRegistration other = (ResourceRegistration) o;

		return this.getListenerType() == other.getListenerType() && this.getResourceName().equals(other.getResourceName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode()
	{
		return 31 * (31 + this.getListenerType().hashCode()) + this.getResourceName().hashCode();
	}
	
	@Override
	public final String toString()
	{
		return Utilities.toString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isRegistered()
	{
		return Resources.isRegistered(this.getListenerType(), this.getResource());
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isProvided()
	{
		return Services.getResourceService().getResourceProvider().names().contains(this.getResourceName());
	}
}
