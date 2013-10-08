package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import static com.gratex.perconik.activity.DataTransferObjects.setProjectData;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.services.activity.IVsActivityWatcherService;
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
public final class IdeCodeListener extends IdeListener implements DocumentListener, TextSelectionListener
{
	public IdeCodeListener()
	{
	}

	static final void process(final IProject project)
	{
		final IdeCodeOperationDto data = new IdeCodeOperationDto();

		// TODO

		setProjectData(data, project);
		setApplicationData(data);
		setEventData(data);
		
		ActivityServices.performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeCodeOperationAsync(data);
			}
		});
	}
	
	// TODO impl
	
	public final void documentAboutToBeChanged(final DocumentEvent event)
	{
	}

	public final void documentChanged(final DocumentEvent event)
	{
	}

	public final void selectionChanged(final IWorkbenchPart part, final ITextSelection selection)
	{
		
		
		selection.getStartLine();
		selection.getEndLine();
		
		IEditorPart editor = (IEditorPart) part;
	}
}
