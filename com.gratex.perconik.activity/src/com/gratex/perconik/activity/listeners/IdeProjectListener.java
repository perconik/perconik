package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.OPEN;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.ADDED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_CLOSE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_DELETE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_REFRESH;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.PROJECT;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltas;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
import com.google.common.collect.ImmutableSet;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.activity.IVsActivityWatcherService;
import com.gratex.perconik.services.activity.IdeProjectOperationDto;
import com.gratex.perconik.services.activity.IdeProjectOperationTypeEnum;

/**
 * A listener of {@code IdeProjectOperation} events. This listener creates
 * {@link IdeProjectOperationDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeProjectListener extends IdeListener implements ResourceListener, SelectionListener
{
	// TODO switch to --> explorer/a editor/b/file explorer/b --> generates switch-to(a,b,a,b)
	// TODO close --> explorer/a --> generates close(a),open(a)
	
	private final Object lock = new Object();
	
	@GuardedBy("lock")
	private IProject project;
	
	public IdeProjectListener()
	{
	}

	private final boolean updateProject(final IProject project)
	{
		if (project != null)
		{
			synchronized (this.lock)
			{
				if (!project.equals(this.project))
				{
					this.project = project;
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	static final void process(final IProject project, final IdeProjectOperationTypeEnum type)
	{
		final IdeProjectOperationDto data = new IdeProjectOperationDto();

		data.setOperationType(type);
		
		setProjectData(data, project);
		setApplicationData(data);
		setEventData(data);
		
		// TODO rm
		System.out.println("PROJECT: " + project.getFullPath() + " operation: " + type);
		
		ActivityServices.performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeProjectOperationAsync(data);
			}
		});
	}
	
	private static final class ResourceDeltaVisitor implements IResourceDeltaVisitor
	{
		private final ResourceEventType type;
		
		ResourceDeltaVisitor(final ResourceEventType type)
		{
			this.type = type;
		}
		
		public final boolean visit(final IResourceDelta delta)
		{
			ResourceDeltaKind      kind  = ResourceDeltaKind.valueOf(delta.getKind());
			Set<ResourceDeltaFlag> flags = ResourceDeltaFlag.setOf(delta.getFlags());
			
			return this.resolve(delta.getResource(), kind, flags);
		}
		
		final boolean resolve(final IResource resource, @Nullable final ResourceDeltaKind kind, final Set<ResourceDeltaFlag> flags)
		{
			if (resource == null || ResourceType.valueOf(resource.getType()) != PROJECT)
			{
				return true;
			}
			
			switch (this.type)
			{
				case PRE_CLOSE:
					process((IProject) resource, IdeProjectOperationTypeEnum.CLOSE);
					break;

				case PRE_DELETE:
					process((IProject) resource, IdeProjectOperationTypeEnum.REMOVE);
					break;

				case PRE_REFRESH:
					process((IProject) resource, IdeProjectOperationTypeEnum.REFRESH);
					break;

				case POST_CHANGE:
					if (kind == ADDED) process((IProject) resource, IdeProjectOperationTypeEnum.ADD);
					if (flags.contains(OPEN)) process((IProject) resource, IdeProjectOperationTypeEnum.OPEN);
					break;

				default:
					break;
			}
			
			return false;
		}
	}
	
	public final void resourceChanged(final IResourceChangeEvent event)
	{
		ResourceEventType type = ResourceEventType.valueOf(event.getType());

		IResourceDelta delta = event.getDelta();

		if (delta != null)
		{
			ResourceDeltas.accept(delta, new ResourceDeltaVisitor(type));
		}
		else
		{
			new ResourceDeltaVisitor(type).resolve(event.getResource(), null, ImmutableSet.<ResourceDeltaFlag>of());
		}
	}

	public final void selectionChanged(final IWorkbenchPart part, final ISelection selection)
	{
		IProject project = part instanceof IEditorPart ? Projects.getProject((IEditorPart) part) : null;
		
		if (project != null) System.out.println("PROJECT -> editor"); // TODO rm
		
		if (project == null && selection instanceof IStructuredSelection)
		{
			project = Projects.getProject((IStructuredSelection) selection);
			if (project != null) System.out.println("PROJECT -> structured selection"); // TODO rm
		}
		
		if (project == null)
		{
			project = Projects.getProject(part.getSite().getPage());
			if (project != null) System.out.println("PROJECT -> part -> page"); // TODO rm
		}
		
		if (this.updateProject(project))
		{
			process(project, IdeProjectOperationTypeEnum.SWITCH_TO);
		}
	}

	public final Set<ResourceEventType> getEventTypes()
	{
		return ImmutableSet.of(PRE_CLOSE, PRE_DELETE, PRE_REFRESH, POST_CHANGE);
	}
}
