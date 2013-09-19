package com.gratex.perconik.activity.listeners;

import java.util.EnumSet;
import java.util.Set;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import com.gratex.perconik.services.activity.IdeDocumentOperationDto;

/**
 * A listener of {@code IdeDocumentOperation} events. This listener creates
 * {@link IdeDocumentOperationDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeDocumentListener extends IdeListener implements ResourceListener
{
	public IdeDocumentListener()
	{
	}
	
	// TODO impl

	public final void resourceChanged(final IResourceChangeEvent event)
	{
		IResource res = event.getResource();
		
		try
		{
			switch (event.getType())
			{
				case IResourceChangeEvent.PRE_CLOSE:
					System.out.print("Project ");
					System.out.print(res.getFullPath());
					System.out.println(" is about to close.");
					break;
				case IResourceChangeEvent.PRE_DELETE:
					System.out.print("Project ");
					System.out.print(res.getFullPath());
					System.out.println(" is about to be deleted.");
					break;
				case IResourceChangeEvent.POST_CHANGE:
					System.out.println("Resources have changed.");
					event.getDelta().accept(new DeltaPrinter());
					break;
				case IResourceChangeEvent.PRE_BUILD:
					System.out.println("Build about to run.");
					event.getDelta().accept(new DeltaPrinter());
					break;
				case IResourceChangeEvent.POST_BUILD:
					System.out.println("Build complete.");
					event.getDelta().accept(new DeltaPrinter());
					break;
			}
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
	}

	class DeltaPrinter implements IResourceDeltaVisitor
	{
		public boolean visit(IResourceDelta delta)
		{
			IResource res = delta.getResource();
			switch (delta.getKind())
			{
				case IResourceDelta.ADDED:
					System.out.print("Resource ");
					System.out.print(res.getFullPath());
					System.out.println(" was added.");
					break;
				case IResourceDelta.REMOVED:
					System.out.print("Resource ");
					System.out.print(res.getFullPath());
					System.out.println(" was removed.");
					break;
				case IResourceDelta.CHANGED:
					System.out.print("Resource ");
					System.out.print(delta.getFullPath());
					System.out.println(" has changed.");
					int flags = delta.getFlags();
					if ((flags & IResourceDelta.CONTENT) != 0)
					{
						System.out.println("--> Content Change");
					}
					if ((flags & IResourceDelta.REPLACED) != 0)
					{
						System.out.println("--> Content Replaced");
					}
					if ((flags & IResourceDelta.MARKERS) != 0)
					{
						System.out.println("--> Marker Change");
						IMarkerDelta[] markers = delta.getMarkerDeltas();
						// if interested in markers, check these deltas
					}
					break;			}
			return true; // visit the children
		}
	}

	public final Set<ResourceEventType> getEventTypes()
	{
		//return ImmutableSet.of(ResourceEventType.POST_CHANGE);
		return EnumSet.allOf(ResourceEventType.class);
	}
}
