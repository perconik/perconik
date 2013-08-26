package sk.stuba.fiit.perconik.core.resources;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

final class StandardResource<L extends Listener> extends AbstractResource<L>
{
	StandardResource(final Pool<L> pool)
	{
		super(pool);
	}

	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}
		
		if (!(o instanceof Resource))
		{
			return false;
		}
		
		Resource<?> other = (Resource<?>) o;
		
		return this.getName().equals(other.getName());
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
