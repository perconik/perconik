package sk.stuba.fiit.perconik.core.persistence.data;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.persistence.ResourceRegistration;
import sk.stuba.fiit.perconik.core.services.Services;

public abstract class AbstractResourceRegistration implements ResourceRegistration
{
	protected AbstractResourceRegistration()
	{
	}
	
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

	public final boolean isRegistered()
	{
		return Resources.isRegistered(this.getListenerType(), this.getResource());
	}

	public final boolean isProvided()
	{
		return Services.getResourceService().getResourceProvider().names().contains(this.getResourceName());
	}
}
