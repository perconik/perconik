package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import java.util.EnumSet;
import java.util.Set;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import sk.stuba.fiit.perconik.core.listeners.ResourceListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceEventType;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;
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
public final class IdeDocumentListener extends IdeListener implements ResourceListener
{
	public IdeDocumentListener()
	{
	}
	
	// TODO impl

	public final void resourceChanged(final IResourceChangeEvent event)
	{
//		IResource resource = event.getResource();
//		
//		if (ResourceType.valueOf(resource.getType()) != ResourceType.FILE)
//		{
//			return;
//		}
//		
//		IdeDocumentOperationTypeEnum type;
//		
//		final IdeDocumentOperationDto data = new IdeDocumentOperationDto();
//		
//		switch (ResourceEventType.valueOf(event.getType()))
//		{
//			case PRE_DELETE:
//				type = IdeDocumentOperationTypeEnum.REMOVE;
//				break;
//			
//			case PRE_CLOSE:
//				type = IdeDocumentOperationTypeEnum.CLOSE;
//				break;
//				
//			default:
//				break;
//		}
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

	public final Set<ResourceEventType> getEventTypes()
	{
		return EnumSet.allOf(ResourceEventType.class);
	}
}