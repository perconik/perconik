package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import java.util.Collection;
import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.activity.IVsActivityWatcherService;
import com.gratex.perconik.services.activity.IdeStateChangeDto;

/**
 * A listener of {@code IdeStateChange} events. This listener creates
 * {@link IdeStateChangeDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeStateListener extends IdeListener implements LaunchListener, PerspectiveListener
{
	public IdeStateListener()
	{
	}
	
	public final void launchAdded(final ILaunch launch)
	{
		final IdeStateChangeDto data = new IdeStateChangeDto();

		data.setStateType(launch.getLaunchMode() + " (launch)");

		Collection<IProject> projects = Projects.getProjects(launch);
		
		setProjectData(data, projects.iterator().next());
		setApplicationData(data);
		setEventData(data);
		
		ActivityServices.performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeStateChangeAsync(data);
			}
		});
	}

	public final void launchRemoved(final ILaunch launch)
	{
	}

	public final void launchChanged(final ILaunch launch)
	{
	}

	public final void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor)
	{
		final IdeStateChangeDto data = new IdeStateChangeDto();

		// TODO rm
//		ActivityApplication application = ActivityApplication.getInstance();
//
//		data.setIsMilestone(true);
//		data.setTime(TimeSupplier.getInstance().get());
//
//		data.setIdePid(application.getPid());
//		data.setApplicationName(application.getName());
//		data.setApplicationVersion(application.getVersion());
//
//		data.setProjectName(Projects.getProject(page).getName());
//		data.setSolutionName(Workspaces.getName(Projects.getProject(page).getWorkspace()));

		data.setStateType(descriptor.getLabel().toLowerCase() + " (perspective)");

		setProjectData(data, Projects.getProject(page));
		setApplicationData(data);
		setEventData(data);
		
		ActivityServices.performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeStateChangeAsync(data);
			}
		});
	}

	public final void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final String change)
	{
	}

	public final void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor descriptor, final IWorkbenchPartReference reference, final String change)
	{
	}

	public final void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after)
	{
	}
}
