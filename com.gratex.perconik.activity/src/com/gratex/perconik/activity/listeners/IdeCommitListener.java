package com.gratex.perconik.activity.listeners;

import com.gratex.perconik.services.activity.IdeCheckinDto;

/**
 * A listener of {@code IdeCommit} events. This listener creates
 * {@link IdeCheckinDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeCommitListener extends IdeListener
{
	public IdeCommitListener()
	{
	}
}
