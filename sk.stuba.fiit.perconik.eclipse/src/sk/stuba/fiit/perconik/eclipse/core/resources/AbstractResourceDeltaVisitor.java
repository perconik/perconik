package sk.stuba.fiit.perconik.eclipse.core.resources;

import javax.annotation.Nullable;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;

public abstract class AbstractResourceDeltaVisitor implements IResourceDeltaVisitor
{
	protected AbstractResourceDeltaVisitor()
	{
	}
	
	public final boolean visit(final IResourceDelta delta)
	{
		return this.resolveDelta(delta, delta.getResource());
	}
	
	public final boolean handle(@Nullable final IResource resource)
	{
		if (resource == null)
		{
			return false;
		}
		
		return this.resolveResource(resource);
	}
	
	protected abstract boolean resolveDelta(IResourceDelta delta, IResource resource);

	protected abstract boolean resolveResource(IResource resource);
	
	public final void visitOrHandle(@Nullable final IResourceDelta delta, @Nullable final IResourceChangeEvent event)
	{
		this.preVisitOrHandle();
		
		if (delta != null)
		{
			ResourceDeltas.accept(delta, this);
		}
		else
		{
			this.handle(event.getResource());
		}
		
		this.postVisitOrHandle();
	}

	protected void preVisitOrHandle()
	{
	}

	protected void postVisitOrHandle()
	{
	}
}
