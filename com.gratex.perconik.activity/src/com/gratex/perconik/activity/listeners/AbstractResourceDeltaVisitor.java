package com.gratex.perconik.activity.listeners;

import javax.annotation.Nullable;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;

abstract class AbstractResourceDeltaVisitor implements IResourceDeltaVisitor
{
	final ResourceEventType type;
	
	AbstractResourceDeltaVisitor(final ResourceEventType type)
	{
		assert type != null;
		
		this.type = type;
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
	
	abstract boolean resolveDelta(IResourceDelta delta, IResource resource);

	abstract boolean resolveResource(IResource resource);
}
