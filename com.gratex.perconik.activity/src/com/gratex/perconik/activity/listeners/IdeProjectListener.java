package com.gratex.perconik.activity.listeners;

import sk.stuba.fiit.perconik.eclipse.core.resources.Workspaces;
import com.gratex.perconik.services.activity.IdeProjectOperationDto;

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
public final class IdeProjectListener extends IdeListener
{
	public IdeProjectListener()
	{
	}
	
	void test()
	{
		Workspaces.getWorkspace().getRoot().getProjects();
	}
}
