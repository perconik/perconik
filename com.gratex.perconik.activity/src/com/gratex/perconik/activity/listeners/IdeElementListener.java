package com.gratex.perconik.activity.listeners;

import com.gratex.perconik.services.activity.IdeCodeElementEventDto;

/**
 * A listener of {@code IdeCodeElement} events. This listener creates
 * {@link IdeCodeElementEventDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeElementListener extends IdeListener
{
	public IdeElementListener()
	{
	}
}
