package sk.stuba.fiit.perconik.core.resources;

import javax.annotation.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import sk.stuba.fiit.perconik.core.AbstractRegistrable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

abstract class AbstractResource<L extends Listener> extends AbstractRegistrable implements Resource<L>
{
	final String name;
	
	AbstractResource(final String name)
	{
		Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
		
		this.name = name;
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
