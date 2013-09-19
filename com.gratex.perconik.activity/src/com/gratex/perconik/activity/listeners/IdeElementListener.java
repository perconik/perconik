package com.gratex.perconik.activity.listeners;

import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType.POST_CHANGE;
import static sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType.POST_RECONCILE;
import java.util.Set;
import org.eclipse.jdt.core.ElementChangedEvent;
import sk.stuba.fiit.perconik.core.listeners.JavaElementListener;
import sk.stuba.fiit.perconik.eclipse.jdt.core.JavaElementEventType;
import com.google.common.collect.ImmutableSet;
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
public final class IdeElementListener extends IdeListener implements JavaElementListener
{
	public IdeElementListener()
	{
	}
	
	// TODO impl

	public void elementChanged(ElementChangedEvent event)
	{
	}

	public Set<JavaElementEventType> getEventTypes()
	{
		return ImmutableSet.of(POST_CHANGE, POST_RECONCILE);
	}
}
