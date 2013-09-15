package com.gratex.perconik.activity;

import org.eclipse.core.resources.IProject;
import sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces;
import com.gratex.perconik.services.activity.EventDto;
import com.gratex.perconik.services.activity.IdeEventDto;
import com.gratex.perconik.services.activity.IdeSlnPrjEventDto;

public final class DataTransferObjects
{
	private DataTransferObjects()
	{
		throw new AssertionError();
	}
	
	public static final void setEventData(final EventDto data)
	{
		data.setIsMilestone(MilestoneResolver.getInstance().isMilestone(data));
		data.setTime(TimeSupplier.getInstance().get());
	}

	public static final void setApplicationData(final IdeEventDto data)
	{
		Application application = Application.getInstance();
		
		data.setIdePid(application.getPid());
		data.setApplicationName(application.getName());
		data.setApplicationVersion(application.getVersion());
	}
	
	public static final void setProjectData(final IdeSlnPrjEventDto data, final IProject project)
	{
		data.setProjectName(project.getName());
		data.setSolutionName(Workspaces.getName(project.getWorkspace()));
	}
}
