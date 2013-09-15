package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.eclipse.core.resources.Projects;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.activity.IVsActivityWatcherService;
import com.gratex.perconik.services.activity.IdeStateChangeDto;

public final class IdeStateListener extends Adapter implements LaunchListener, PerspectiveListener
{
	public IdeStateListener()
	{
	}
	
	public final void launchAdded(ILaunch launch)
	{
		final IdeStateChangeDto data = new IdeStateChangeDto();

		data.setStateType(launch.getLaunchMode() + " (launch)");
		
		//setProjectData(data, Projects.getProject(page)); // TODO fix
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

	public final void launchRemoved(ILaunch launch)
	{
	}

	public final void launchChanged(ILaunch launch)
	{
	}

	public final void perspectiveOpened(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectiveClosed(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
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

	public final void perspectiveDeactivated(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectivePreDeactivate(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public final void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor descriptor, String change)
	{
	}

	public final void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor descriptor, IWorkbenchPartReference reference, String change)
	{
	}

	public final void perspectiveSavedAs(IWorkbenchPage page, IPerspectiveDescriptor before, IPerspectiveDescriptor after)
	{
	}
}
