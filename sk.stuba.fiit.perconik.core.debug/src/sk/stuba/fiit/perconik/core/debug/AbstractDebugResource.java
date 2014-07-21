package sk.stuba.fiit.perconik.core.debug;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;

public abstract class AbstractDebugResource<L extends Listener> extends AbstractDebugRegistrable implements DebugResource<L>
{
	private final String name = this.getClass().getName();
	
	protected AbstractDebugResource()
	{
	}
	
	protected AbstractDebugResource(final DebugConsole console)
	{
		super(console);
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
		return this.name;
	}
}
