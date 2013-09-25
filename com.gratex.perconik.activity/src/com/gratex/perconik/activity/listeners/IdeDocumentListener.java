package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.MOVED_FROM;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.MOVED_TO;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.OPEN;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.REPLACED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.ADDED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.REMOVED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_CLOSE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_DELETE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.PRE_REFRESH;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.FILE;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.java.JavaElements;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltas;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.activity.IVsActivityWatcherService;
import com.gratex.perconik.services.activity.IdeDocumentOperationDto;
import com.gratex.perconik.services.activity.IdeDocumentOperationTypeEnum;

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
public final class IdeDocumentListener extends IdeListener implements ResourceListener, SelectionListener
{
	private final Object lock = new Object();
	
	@GuardedBy("lock")
	private IFile file;
	
	public IdeDocumentListener()
	{
	}
	
	private final boolean updateFile(final IFile file)
	{
		if (file != null)
		{
			synchronized (this.lock)
			{
				if (!file.equals(this.file))
				{
					this.file = file;
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	// TODO impl

	static final void process(final IFile file, final IdeDocumentOperationTypeEnum type)
	{
		final IdeDocumentOperationDto data = new IdeDocumentOperationDto();

		data.setOperationType(type);
		
		setProjectData(data, file.getProject());
		setApplicationData(data);
		setEventData(data);
		
		// TODO rm
		System.out.println("DOCUMENT: " + file.getFullPath() + " operation: " + type);
		
		ActivityServices.performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeDocumentOperation(data);
			}
		});
	}
	
	// TODO if too similar with IdeProjectListener -> refactor into IdeResourceListener
	
	private static final class ResourceDeltaVisitor implements IResourceDeltaVisitor
	{
		private final ResourceEventType type;
		
		ResourceDeltaVisitor(final ResourceEventType type)
		{
			this.type = type;
		}

		public final boolean visit(final IResourceDelta delta)
		{
			return this.resolve(delta, delta.getResource());
		}
		
		final boolean resolve(@Nullable final IResourceDelta delta, final IResource resource)
		{
			if (resource == null || ResourceType.valueOf(resource.getType()) != FILE)
			{
				return true;
			}
			
			switch (this.type)
			{
				case PRE_CLOSE:
					process((IFile) resource, IdeDocumentOperationTypeEnum.CLOSE);
					break;

				case PRE_DELETE:
					process((IFile) resource, IdeDocumentOperationTypeEnum.REMOVE);
					break;

				case POST_CHANGE:
					if (delta == null) break;

					ResourceDeltaKind      kind  = ResourceDeltaKind.valueOf(delta.getKind());
					Set<ResourceDeltaFlag> flags = ResourceDeltaFlag.setOf(delta.getFlags());

					if (flags.contains(REPLACED))
					{
						process((IFile) resource, IdeDocumentOperationTypeEnum.REMOVE);
						process((IFile) resource, IdeDocumentOperationTypeEnum.ADD);
					}
					
					if (flags.contains(MOVED_TO) || flags.contains(MOVED_FROM))
					{
						IPath path = Objects.firstNonNull(delta.getMovedToPath(), delta.getMovedFromPath());
						
						System.out.println("LAST SEGMENT "+resource.getFullPath().lastSegment());
						
						if (!path.lastSegment().equals(resource.getFullPath().lastSegment()))
						{
							process((IFile) resource, IdeDocumentOperationTypeEnum.RENAME);
						}
					}
					
					if (kind == ADDED)   process((IFile) resource, IdeDocumentOperationTypeEnum.ADD);
					if (kind == REMOVED) process((IFile) resource, IdeDocumentOperationTypeEnum.REMOVE);
					
					if (flags.contains(OPEN)) process((IFile) resource, IdeDocumentOperationTypeEnum.OPEN);
					break;

				default:
					break;
			}
			
			return false;
		}
	}

	public final void resourceChanged(final IResourceChangeEvent event)
	{
		ResourceEventType type  = ResourceEventType.valueOf(event.getType());
		IResourceDelta    delta = event.getDelta();

		if (delta != null)
		{
			ResourceDeltas.accept(delta, new ResourceDeltaVisitor(type));
		}
		else
		{
			new ResourceDeltaVisitor(type).resolve(null, event.getResource());
		}
	}

	public final void selectionChanged(final IWorkbenchPart part, final ISelection selection)
	{
		IFile file = null;

		if (selection instanceof StructuredSelection)
		{
			Object element = ((StructuredSelection) selection).getFirstElement();

			if (element instanceof IFile)
			{
				file = (IFile) element;
			}
			
			if (element instanceof IJavaElement)
			{
				IResource resource = JavaElements.resource((IJavaElement) element);

				if (resource instanceof IFile)
				{
					file = (IFile) resource;
				}
			}
		}
		
		if (file == null && part instanceof IEditorPart)
		{
			file = Editors.getFile((IEditorPart) part);
		}
		
		if (this.updateFile(file))
		{
			process(file, IdeDocumentOperationTypeEnum.SWITCH_TO);
		}
	}

	public final Set<ResourceEventType> getEventTypes()
	{
		return ImmutableSet.of(PRE_CLOSE, PRE_DELETE, PRE_REFRESH, POST_CHANGE);
	}
}
