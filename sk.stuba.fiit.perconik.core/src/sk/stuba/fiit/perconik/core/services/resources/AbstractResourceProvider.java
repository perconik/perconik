package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Throwables;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.AbstractProvider;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;

/**
 * An abstract implementation of {@link ResourceProvider}.
 * 
 * TODO doc providing process, class loading / instantiation
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractResourceProvider extends AbstractProvider implements ResourceProvider
{
	// TODO add javadocs
	
	/**
	 * Constructor for use by subclasses.
	 */
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
	
	protected final Resource<?> parentForName(final String name, @Nullable final Exception cause)
	{
		try
		{
			return this.parentOrFailure().forName(name);
		}
		catch (Exception e)
		{
			throw Throwables.propagate(MoreThrowables.initializeSuppressor(e, cause));
		}
	}
	
	protected final <L extends Listener> Set<Resource<L>> parentForType(final Class<L> type, @Nullable final Exception cause)
	{
		try
		{
			return this.parentOrFailure().forType(type);
		}
		catch (Exception e)
		{
			throw Throwables.propagate(MoreThrowables.initializeSuppressor(e, cause));
		}
	}
}
