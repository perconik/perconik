package com.gratex.perconik.activity.ide.listeners;

import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import static com.gratex.perconik.activity.ide.listeners.Utilities.currentTime;
import static com.gratex.perconik.activity.ide.listeners.Utilities.dereferenceEditor;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.MOVED_TO;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag.OPEN;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.ADDED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind.REMOVED;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.FILE;
import static sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType.PROJECT;
import java.util.Map.Entry;
import java.util.Objects;
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
import org.eclipse.jdt.core.IClassFile;
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
import sk.stuba.fiit.perconik.eclipse.core.resources.AbstractResourceDeltaVisitor;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaFlag;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceDeltaKind;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.SetMultimap;
import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.IVsActivityWatcherService;
import com.gratex.perconik.services.uaca.vs.IdeDocumentOperationDto;
import com.gratex.perconik.services.uaca.vs.IdeDocumentOperationTypeEnum;
import com.gratex.perconik.services.uaca.vs.IdePathTypeEnum;
import com.gratex.perconik.services.uaca.vs.IdeProjectOperationTypeEnum;

/**
 * A listener of {@code IdeDocumentOperation} events. This listener creates
 * {@link IdeDocumentOperationDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p>Document operation types that this listener is interested in are
 * determined by the {@link IdeDocumentOperationTypeEnum} enumeration:
 * 
 * <ul>
 *   <li>Add - a document is added into the project.
 *   <li>Close - an opened document is closed.
 *   <li>Open - a closed document is opened.
 *   <li>Remove - a document is removed from the project.
 *   <li>Rename - currently not supported.
 *   <li>Save - a document is saved.
 *   <li>Switch to - focus is changed from one document to another,
 *   editor selections (tabs and text) and structured selections in
 *   package explorer are supported.
 * </ul>
 * 
 * <p>Data available in an {@code IdeDocumentOperationDto}:
 * 
 * <ul>
 *   <li>{@code document} - see {@code IdeDocumentDto} below.
 *   <li>{@code operationType} - see {@link IdeProjectOperationTypeEnum}
 *   for all possible values in this field.
 *   <li>See {@link IdeListener} for documentation of inherited data.
 * </ul>
 * 
 * <p>Data available in an {@code IdeDocumentDto}:
 * 
 * <ul>
 *   <li>{@code branchName} - current Git branch name for the document.
 *   <li>{@code changesetIdInRcs} - most recent Git commit
 *   identifier for the document (40 hexadecimal characters),
 *   for example {@code "984dd5f359532d7d806a92b47ef5bfc39d772d64"}.
 *   <li>{@code id} - unused but exposed internals.
 *   <li>{@code path} - path to the document relative to the workspace root,
 *   for example {@code "com.gratex.perconik.activity/src/com/gratex/perconik/activity/ide/listeners/IdeCommitListener.java"}.
 *   <li>{@code pathType} - always {@link IdePathTypeEnum#RELATIVE_LOCAL
 *   RELATIVE_LOCAL}.
 *   <li>{@code rcsServer} - see documentation of {@code RcsServerDto}
 *   in {@link IdeCommitListener} for more details.
 * </ul>
 * 
 * <p>Note that in case of not editable source code, such as classes from JRE
 * system library, fields {@code branchName}, {@code changesetIdInRcs},
 * and {@code rcsServer} are unused and set to {@code null}.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeDocumentListener extends IdeListener implements EditorListener, FileBufferListener, ResourceListener, SelectionListener
{
	// TODO note that switch_to is generated before open/close 
	// TODO open is also generated on initial switch to previously opened tab directly after eclipse launch 
	
	private final Object lock = new Object();
	
	@GuardedBy("lock")
	private UnderlyingDocument<?> document;
	
	public IdeDocumentListener()
	{
	}
	
	private final boolean updateFile(final UnderlyingDocument<?> document)
	{
		if (document != null)
		{
			synchronized (this.lock)
			{
				if (!document.equals(this.document))
				{
					this.document = document;
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	static final void send(final IdeDocumentOperationDto data)
	{
		performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeDocumentOperation(data);
			}
		});
	}

	static final IdeDocumentOperationDto build(final long time, final IFile file, final IdeDocumentOperationTypeEnum type)
	{
		return build(time, UnderlyingDocument.of(file), type);
	}
	
	static final IdeDocumentOperationDto build(final long time, final UnderlyingDocument<?> document, final IdeDocumentOperationTypeEnum type)
	{
		final IdeDocumentOperationDto data = new IdeDocumentOperationDto();

		data.setOperationType(type);
		
		document.setDocumentData(data);
		document.setProjectData(data);

		setApplicationData(data);
		setEventData(data, time);

		if (Log.enabled()) Log.message().appendln("document: " + document.getPath() + " operation: " + type).appendTo(console);
		
		return data;
	}
	
	private static final class ResourceDeltaVisitor extends AbstractResourceDeltaVisitor
	{
		private final long time;
		
		private final ResourceEventType type;

		private final SetMultimap<IdeDocumentOperationTypeEnum, IFile> operations;
		
		ResourceDeltaVisitor(final long time, final ResourceEventType type)
		{
			assert time >= 0;
			assert type != null;
			
			this.time = time;
			this.type = type;

			this.operations = LinkedHashMultimap.create(3, 2);
		}

		@Override
		protected final boolean resolveDelta(final IResourceDelta delta, final IResource resource)
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
				
				if (!Objects.equals(path.lastSegment(), resource.getFullPath().lastSegment()))
				{
					this.operations.put(IdeDocumentOperationTypeEnum.RENAME, (IFile) resource);
					
					return false;
				}
			}
			
			ResourceDeltaKind kind = ResourceDeltaKind.valueOf(delta.getKind());

			if (kind == ADDED)   this.operations.put(IdeDocumentOperationTypeEnum.ADD,    (IFile) resource);
			if (kind == REMOVED) this.operations.put(IdeDocumentOperationTypeEnum.REMOVE, (IFile) resource);

			return false;
		}

		@Override
		protected final boolean resolveResource(final IResource resource)
		{
			return false;
		}

		@Override
		protected final void postVisitOrHandle()
		{
			if (this.operations.containsKey(IdeDocumentOperationTypeEnum.RENAME))
			{
				this.operations.removeAll(IdeDocumentOperationTypeEnum.ADD);
			}
			
			for (Entry<IdeDocumentOperationTypeEnum, IFile> entry: this.operations.entries())
			{
				send(build(this.time, entry.getValue(), entry.getKey()));
			}
		}
	}

	static final void process(final long time, final IResourceChangeEvent event)
	{
		ResourceEventType type  = ResourceEventType.valueOf(event.getType());
		IResourceDelta    delta = event.getDelta();

		new ResourceDeltaVisitor(time, type).visitOrHandle(delta, event);
	}
	
	final void process(final long time, final IWorkbenchPart part, final ISelection selection)
	{
		UnderlyingDocument<?> document = null;

		if (selection instanceof StructuredSelection)
		{
			Object element = ((StructuredSelection) selection).getFirstElement();

			if (element instanceof IFile)
			{
				document = UnderlyingDocument.of((IFile) element);
			}
			else if (element instanceof IClassFile)
			{
				document = UnderlyingDocument.of((IClassFile) element);
			}
			else if (element instanceof IJavaElement)
			{
				IResource resource = JavaElements.resource((IJavaElement) element);

				if (resource instanceof IFile)
				{
					document = UnderlyingDocument.of((IFile) resource);
				}
			}
		}
		
		if (document == null && part instanceof IEditorPart)
		{
			document = UnderlyingDocument.from((IEditorPart) part);
		}
		
		if (this.updateFile(document))
		{
			send(build(time, document, IdeDocumentOperationTypeEnum.SWITCH_TO));
		}
	}
	
	@Override
	public final void postRegister()
	{
		executeSafely(new Runnable()
		{
			@Override
			public final void run()
			{
				final UnderlyingDocument<?> document = UnderlyingDocument.from(Editors.getActiveEditor());

				if (document == null)
				{
					return;
				}
				
				send(build(currentTime(), document, IdeDocumentOperationTypeEnum.OPEN));
			}
		});
	}

	public final void resourceChanged(final IResourceChangeEvent event)
	{
		final long time = currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				process(time, event);
			}
		});
	}

	public final void selectionChanged(final IWorkbenchPart part, final ISelection selection)
	{
		final long time = currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				process(time, part, selection);
			}
		});
	}

	public final void editorOpened(final IEditorReference reference)
	{
		final long time = currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				UnderlyingDocument<?> resource = UnderlyingDocument.from(dereferenceEditor(reference));
				
				if (resource != null)
				{
					send(build(time, resource, IdeDocumentOperationTypeEnum.OPEN));
				}
			}
		});
	}

	public final void editorClosed(final IEditorReference reference)
	{
		final long time = currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				UnderlyingDocument<?> document = UnderlyingDocument.from(dereferenceEditor(reference));
				
				if (document != null)
				{
					send(build(time, document, IdeDocumentOperationTypeEnum.CLOSE));
				}
			}
		});
	}

	public final void editorActivated(final IEditorReference reference)
	{
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
		final long time = currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				if (!dirty)
				{
					IFile file = FileBuffers.getWorkspaceFileAtLocation(buffer.getLocation());
					
					send(build(time, file, IdeDocumentOperationTypeEnum.SAVE));
				}
			}
		});
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
