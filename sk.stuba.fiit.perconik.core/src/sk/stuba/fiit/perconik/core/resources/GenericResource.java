package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

final class GenericResource<T extends Listener> extends AbstractResource<T>
{
	private final String name;
	
	GenericResource(final Pool<T> pool)
	{
		super(pool);
		
		this.name = name(pool);
	}
	
	private static final String name(final Pool<?> pool)
	{
		return pool.toString().replace("Pool", "") + "Resource";
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
