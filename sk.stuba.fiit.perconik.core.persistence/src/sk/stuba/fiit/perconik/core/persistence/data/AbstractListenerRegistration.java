package sk.stuba.fiit.perconik.core.persistence.data;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.persistence.ListenerRegistration;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotables;

/**
 * An abstract implementation of the {@link ListenerRegistration} interface.
 * Implemented predicates like the current registration status are obtained
 * directly from the core using the underlying listener or listener's data.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractListenerRegistration extends AbstractAnnotableRegistration implements ListenerRegistration
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected AbstractListenerRegistration()
	{
	}

	/**
	 * Returns the backing annotable delegate instance.
	 */
	@Override
	protected final Annotable delegate()
	{
		return Annotables.fromAnnotations(Utilities.collect(this.getListener()));
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
		
		if (!(o instanceof ListenerRegistration))
		{
			return false;
		}

		ListenerRegistration other = (ListenerRegistration) o;

		return this.getListenerClass() == other.getListenerClass();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode()
	{
		return 31 * this.getListenerClass().hashCode();
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
		return Listeners.isRegistered(this.getListener());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final boolean isProvided()
	{
		return Services.getListenerService().getListenerProvider().classes().contains(this.getListenerClass());
	}
}
