package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.MOVED_TO;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.OPEN;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.ADDED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.REMOVED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.FILE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.PROJECT;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.java.JavaElements;
import sk.stuba.fiit.perconik.core.java.JavaProjects;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltas;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import com.google.common.collect.ImmutableSet;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.activity.DataTransferObjects;
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
public final class IdeDocumentListener extends IdeListener implements EditorListener, FileBufferListener, ResourceListener, SelectionListener
{
	// TODO rename generates rename + add
	// TODO note that switch_to is generated before open/close 
	// TODO open is also generated on initial switch to previously opened tab directly after eclipse launch 
	
	private final Object lock = new Object();
	
	@GuardedBy("lock")
	private IFile file;
	
	public IdeDocumentListener()
	{
	}
	
	private static final IEditorPart dereferenceEditor(final IEditorReference reference)
	{
		return reference.getEditor(false);
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

	static final void process(final IFile file, final IdeDocumentOperationTypeEnum type)
	{
		final IdeDocumentOperationDto data = new IdeDocumentOperationDto();

		data.setDocument(DataTransferObjects.newDocumentData(file));
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
	
	private static final class ResourceDeltaVisitor extends AbstractResourceDeltaVisitor
	{
		ResourceDeltaVisitor(final ResourceEventType type)
		{
			super(type);
		}

		@Override
		final boolean resolveDelta(final IResourceDelta delta, final IResource resource)
		{
			assert delta != null && resource != null;
			
			if (this.type != POST_CHANGE)
			{
				return false;
			}
			
			IProject project = resource.getProject();
			
			if (project != null && JavaProjects.inOutputLocation(project, resource))
			{
				return false;
			}
			
			ResourceType type = ResourceType.valueOf(resource.getType());
			
			Set<ResourceDeltaFlag> flags = ResourceDeltaFlag.setOf(delta.getFlags());
			
			if (type == PROJECT && flags.contains(OPEN))
			{
				return false;
			}
			
			if (type != FILE)
			{
				return true;
			}
			
			if (flags.contains(MOVED_TO))
			{
				IPath path = delta.getMovedToPath();
				
				if (!path.lastSegment().equals(resource.getFullPath().lastSegment()))
				{
					process((IFile) resource, IdeDocumentOperationTypeEnum.RENAME);
					
					return false;
				}
			}
			
			ResourceDeltaKind kind = ResourceDeltaKind.valueOf(delta.getKind());

			if (kind == ADDED)   process((IFile) resource, IdeDocumentOperationTypeEnum.ADD);
			if (kind == REMOVED) process((IFile) resource, IdeDocumentOperationTypeEnum.REMOVE);

			return false;
		}

		@Override
		final boolean resolveResource(final IResource resource)
		{
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
			new ResourceDeltaVisitor(type).handle(event.getResource());
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

	public final void editorOpened(final IEditorReference reference)
	{
		IFile file = Editors.getFile(dereferenceEditor(reference));
		
		if (file != null)
		{
			process(file, IdeDocumentOperationTypeEnum.OPEN);
		}
	}

	public final void editorClosed(final IEditorReference reference)
	{
		IFile file = Editors.getFile(dereferenceEditor(reference));
		
		if (file != null)
		{
			process(file, IdeDocumentOperationTypeEnum.CLOSE);
		}
	}

	public final void editorActivated(final IEditorReference reference)
	{
//		IFile file = Editors.getFile(dereferenceEditor(reference));
//		
//		if (this.updateFile(file))
//		{
//			process(file, IdeDocumentOperationTypeEnum.SWITCH_TO);
//		}
	}

	public final void editorDeactivated(final IEditorReference reference)
	{
	}

	public final void editorVisible(final IEditorReference reference)
	{
	}

	public final void editorHidden(final IEditorReference reference)
	{
	}

	public final void editorBroughtToTop(final IEditorReference reference)
	{
	}

	public final void editorInputChanged(final IEditorReference reference)
	{
	}
	
	public final void bufferCreated(final IFileBuffer buffer)
	{
	}

	public final void bufferDisposed(final IFileBuffer buffer)
	{
	}

	public final void bufferContentAboutToBeReplaced(final IFileBuffer buffer)
	{
	}

	public final void bufferContentReplaced(final IFileBuffer buffer)
	{
	}

	public final void stateChanging(final IFileBuffer buffer)
	{
	}

	public final void stateChangeFailed(final IFileBuffer buffer)
	{
	}

	public final void stateValidationChanged(final IFileBuffer buffer, final boolean stateValidated)
	{
	}

	public final void dirtyStateChanged(final IFileBuffer buffer, final boolean dirty)
	{
		// TODO rm
		//System.out.println("DIRTY "+buffer.isDirty() + " -- "+dirty);
		
		if (!dirty)
		{
			IFile file = FileBuffers.getWorkspaceFileAtLocation(buffer.getLocation());
			
			process(file, IdeDocumentOperationTypeEnum.SAVE);
		}
	}

	public final void underlyingFileMoved(final IFileBuffer buffer, final IPath path)
	{
	}

	public final void underlyingFileDeleted(final IFileBuffer buffer)
	{
	}

	public final Set<ResourceEventType> getEventTypes()
	{
		return ImmutableSet.of(POST_CHANGE);
	}
}
