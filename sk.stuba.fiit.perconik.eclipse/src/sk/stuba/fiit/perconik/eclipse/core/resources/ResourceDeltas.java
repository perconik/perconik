package sk.stuba.fiit.perconik.eclipse.core.resources;

import java.util.Set;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import com.google.common.base.Throwables;

/**
 * Static utility methods pertaining to Eclipse resource deltas.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourceDeltas
{
	private ResourceDeltas()
	{
		throw new AssertionError();
	}
	
	public static final void accept(final IResourceChangeEvent event, final IResourceDeltaVisitor visitor)
	{
		accept(event.getDelta(), visitor);
	}
	
	public static final void accept(final IResourceChangeEvent event, final IResourceDeltaVisitor visitor, final Set<ResourceMemberFlag> flags)
	{
		accept(event.getDelta(), visitor, flags);
	}
	
	public static final void accept(final IResourceDelta delta, final IResourceDeltaVisitor visitor)
	{
		try
		{
			delta.accept(visitor);
		}
		catch (CoreException e)
		{
			Throwables.propagate(e);
		}
	}
	
	public static final void accept(final IResourceDelta delta, final IResourceDeltaVisitor visitor, final Set<ResourceMemberFlag> flags)
	{
		try
		{
			delta.accept(visitor, ResourceMemberFlag.valuesAsInteger(flags));
		}
		catch (CoreException e)
		{
			Throwables.propagate(e);
		}
	}
}
