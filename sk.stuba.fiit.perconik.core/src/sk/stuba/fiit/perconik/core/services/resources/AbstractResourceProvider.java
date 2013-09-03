package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.AbstractProvider;

public abstract class AbstractResourceProvider extends AbstractProvider implements ResourceProvider
{
	protected AbstractResourceProvider()
	{
	}

	protected final ResourceProvider parentOrFailure()
	{
		ResourceProvider parent = this.parent();
		
		if (parent == null)
		{
			throw new IllegalStateException("Provider hierarchy root reached");
		}
		
		return parent;
	}
	
	protected final Resource<?> parentForName(final String name, final Exception cause)
	{
		try
		{
			return this.parentOrFailure().forName(name);
		}
		catch (Exception e)
		{
			e.initCause(cause);
			
			throw e;
		}
	}
	
	protected final <L extends Listener> Set<Resource<L>> parentForType(final Class<L> type, final Exception cause)
	{
		try
		{
			return this.parentOrFailure().forType(type);
		}
		catch (Exception e)
		{
			e.initCause(cause);
			
			throw e;
		}
	}
}
