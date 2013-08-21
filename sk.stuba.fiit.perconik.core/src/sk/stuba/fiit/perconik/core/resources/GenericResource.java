package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

final class GenericResource<L extends Listener> extends AbstractResource<L>
{
	GenericResource(final Pool<L> pool)
	{
		super(pool);
	}

	@Override
	public final boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		
		if (!(o instanceof Resource))
		{
			return false;
		}
		
		return this.getName().equals(((Resource<?>) o).getName());
	}

	@Override
	public final int hashCode()
	{
		return this.getName().hashCode();
	}

	@Override
	public final String toString()
	{
		return this.getName();
	}

	public final String getName()
	{
		return this.pool.toString().replace("Pool", "Resource");
	}
}
