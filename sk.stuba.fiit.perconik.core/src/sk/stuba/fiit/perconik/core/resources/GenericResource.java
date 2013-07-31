package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.Listener;

final class GenericResource<T extends Listener> extends AbstractResource<T>
{
	private final String name;
	
	GenericResource(final Pool<T> pool)
	{
		super(pool);
		
		this.name = pool.toString().replace("Pool", "") + "Resource";
	}

	@Override
	public final String toString()
	{
		return this.getName();
	}

	public final String getName()
	{
		return this.name;
	}
}
