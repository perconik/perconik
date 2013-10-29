package com.gratex.perconik.activity.listeners;

import static com.gratex.perconik.activity.DataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.DataTransferObjects.setEventData;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.java.ClassFiles;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import com.google.common.base.Throwables;
import com.gratex.perconik.activity.ActivityServices;
import com.gratex.perconik.activity.ActivityServices.WatcherServiceOperation;
import com.gratex.perconik.activity.Application;
import com.gratex.perconik.services.vs.IVsActivityWatcherService;
import com.gratex.perconik.services.vs.IdeCodeOperationDto;
import com.gratex.perconik.services.vs.IdeCodeOperationTypeEnum;

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
public final class IdeCodeListener extends IdeListener implements TextSelectionListener
{
	// TODO code operation type PasteFromWeb is not supported
	
	private final Executor executor = Executors.newSingleThreadExecutor();
	
	public IdeCodeListener()
	{
	}

	static final class Selection
	{
		String text;
		
		final Position start = new Position();
		
		final Position end = new Position();
		
		static final class Position
		{
			int line, offset;
		}
	}
	
	static final void process(final UnderlyingDocument<?> document, final Selection selection)
	{
		final IdeCodeOperationDto data = new IdeCodeOperationDto();

		data.setCode(selection.text);
		
		data.setStartColumnIndex(selection.start.offset);
		data.setStartRowIndex(selection.start.line);
		
		data.setEndColumnIndex(selection.end.offset);
		data.setEndRowIndex(selection.end.line);
		
		data.setOperationType(IdeCodeOperationTypeEnum.SELECTION_CHANGED);
		data.setWebUrl(null);

		// TODO rm
		if (Application.getInstance().isDebug()){
		Object x = document.resource;
		System.out.println("DOCUMENT: " + (x instanceof IFile ? ((IFile) x).getFullPath() : ClassFiles.path((IClassFile) x)));
		System.out.println("TEXT: "+selection.text+" FROM "+selection.start.line+":"+selection.start.offset+" TO "+selection.end.line+":"+selection.end.offset);
		}
		
		document.setDocumentData(data);
		document.setProjectData(data);
		
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

	public final void selectionChanged(final IWorkbenchPart part, final ITextSelection selection)
	{
		if (!(part instanceof IEditorPart))
		{
			return;
		}
		
		final Runnable command = new Runnable()
		{
			public final void run()
			{
				IEditorPart editor   = (IEditorPart) part;
				IDocument   document = Editors.getDocument(editor);
				
				UnderlyingDocument<?> resource = UnderlyingDocument.of(editor);
				
				if (document == null || resource == null)
				{
					return;
				}
				
				Selection data = new Selection();
				
				data.text = selection.getText();
				
				data.start.line = selection.getStartLine();
				data.end.line   = selection.getEndLine();
		
				try
				{
					int offset = selection.getOffset();
					
					data.start.offset = offset - document.getLineOffset(data.start.line);
					data.end.offset   = offset + selection.getLength() - document.getLineOffset(data.end.line);
					
					String delimeter = document.getLineDelimiter(data.end.line);
					
					if (delimeter != null && data.text.endsWith(delimeter))
					{
						data.end.line ++;
						data.end.offset = 0;
					}
				}
				catch (BadLocationException e)
				{
					throw Throwables.propagate(e);
				}
				
				process(resource, data);
			}
		};
		
		this.executor.execute(command);
	}
}
