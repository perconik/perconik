package com.gratex.perconik.activity.listeners;

import org.eclipse.jface.text.DocumentEvent;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import com.gratex.perconik.services.activity.IdeCodeOperationDto;

/**
 * A listener of {@code IdeCodeOperation} events. This listener creates
 * {@link IdeCodeOperationDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeCodeListener extends IdeListener implements DocumentListener
{
	public IdeCodeListener()
	{
	}

	// TODO impl
	
	public void documentAboutToBeChanged(DocumentEvent event)
	{
	}

	public void documentChanged(DocumentEvent event)
	{
	}
}
