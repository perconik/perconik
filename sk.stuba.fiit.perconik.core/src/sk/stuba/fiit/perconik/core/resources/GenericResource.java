package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

final class GenericResource<L extends Listener> extends AbstractResource<L>
{
	GenericResource(final Pool<L> pool)
	{
		super(pool);
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
