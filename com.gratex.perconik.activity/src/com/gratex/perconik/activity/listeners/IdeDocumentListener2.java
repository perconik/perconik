package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import java.util.EnumSet;
import java.util.Set;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
import com.google.common.io.Resources;
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
public final class IdeDocumentListener2 extends IdeListener implements FileBufferListener
{
	public IdeDocumentListener2()
	{
	}
	
	// TODO impl

	private static final void process(final IdeDocumentOperationTypeEnum type)
	{
//		final IdeDocumentOperationDto data = new IdeDocumentOperationDto();
//		
//		data.setOperationType(type);
//		
//		setProjectData(data, resource.getProject());
//		setApplicationData(data);
//		setEventData(data);
//		
//		ActivityServices.performWatcherServiceOperation(new WatcherServiceOperation()
//		{
//			public final void perform(final IVsActivityWatcherService service)
//			{
//				service.notifyIdeDocumentOperationAsync(data);
//			}
//		});
	}
	
	public final void bufferCreated(final IFileBuffer buffer)
	{
		process(IdeDocumentOperationTypeEnum.OPEN);
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
	}

	public final void underlyingFileMoved(final IFileBuffer buffer, final IPath path)
	{
	}

	public final void underlyingFileDeleted(IFileBuffer buffer)
	{
	}
}
