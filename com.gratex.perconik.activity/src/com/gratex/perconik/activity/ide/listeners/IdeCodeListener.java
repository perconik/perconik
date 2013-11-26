package com.gratex.perconik.activity.ide.listeners;

import static com.google.common.base.Preconditions.checkArgument;
import static com.gratex.perconik.activity.ide.IdeActivityServices.performWatcherServiceOperation;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.java.ClassFiles;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import com.google.common.base.Throwables;
import com.gratex.perconik.activity.ide.IdeActivityServices.WatcherServiceOperation;
import com.gratex.perconik.activity.ide.IdeApplication;
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
public final class IdeCodeListener extends IdeListener implements DocumentListener, TextSelectionListener
{
	// TODO code operation type PasteFromWeb is not supported

	private final PasteHandler handler;
	
	public IdeCodeListener()
	{
		this.handler = new PasteHandler();
	}

	static final class Region
	{
		final Position start = new Position();
		
		final Position end = new Position();
		
		String text;

		static final class Position
		{
			int line, offset;
		}
		
		static final Region of(final IDocument document, int offset, int length, final String text)
		{
			checkArgument(offset >= 0);
			checkArgument(length > 0);
			checkArgument(text != null);
			
			Region data = new Region();
			
			try
			{
				data.start.line = document.getLineOfOffset(offset);
				data.end.line   = document.getLineOfOffset(offset + length);

				data.start.offset = offset - document.getLineOffset(data.start.line);
				data.end.offset   = offset + length - document.getLineOffset(data.end.line);
				
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

			data.text = text;
			
			return data;
		}
	}

	static final void send(final IdeCodeOperationDto data)
	{
		performWatcherServiceOperation(new WatcherServiceOperation()
		{
			public final void perform(final IVsActivityWatcherService service)
			{
				service.notifyIdeCodeOperation(data);
			}
		});
	}
	
	static final IdeCodeOperationDto build(final long time, final UnderlyingDocument<?> document, final Region region, final IdeCodeOperationTypeEnum type)
	{
		final IdeCodeOperationDto data = new IdeCodeOperationDto();

		data.setCode(region.text);
		
		data.setStartColumnIndex(region.start.offset);
		data.setStartRowIndex(region.start.line);
		
		data.setEndColumnIndex(region.end.offset);
		data.setEndRowIndex(region.end.line);
		
		data.setOperationType(type);
		data.setWebUrl(null);

		// TODO rm
		if (IdeApplication.getInstance().isDebug()){
		Object x = document.resource;
		System.out.println("DOCUMENT: " + (x instanceof IFile ? ((IFile) x).getFullPath() : ClassFiles.path((IClassFile) x)) + "  " + type);
		System.out.println("TEXT: "+region.text+" FROM "+region.start.line+":"+region.start.offset+" TO "+region.end.line+":"+region.end.offset);
		}
		
		document.setDocumentData(data);
		document.setProjectData(data);
		
		setApplicationData(data);
		setEventData(data, time);

		return data;
	}

	static final void process(final long time, final IWorkbenchPart part, final ITextSelection selection)
	{
		if (!(part instanceof IEditorPart))
		{
			return;
		}
		
		IEditorPart editor   = (IEditorPart) part;
		IDocument   document = Editors.getDocument(editor);
		
		UnderlyingDocument<?> resource = UnderlyingDocument.of(editor);
		
		if (document == null || resource == null)
		{
			return;
		}
		
		Region data = Region.of(document, selection.getOffset(), selection.getLength(), selection.getText());
		
		assert data.start.line == selection.getStartLine();
		assert data.end.line   == selection.getEndLine();
		
		send(build(time, resource, data, IdeCodeOperationTypeEnum.SELECTION_CHANGED));
	}
	
	static final void process(final long time, final DocumentEvent event)
	{
		IEditorPart editor   = Editors.getActiveEditor();
		IDocument   document = Editors.getDocument(editor);

		if (!event.getDocument().equals(document))
		{
			// TODO rm
			if (IdeApplication.getInstance().isDebug()){System.out.println("--pasted-- documents not equal");}

			return;
		}
		
		UnderlyingDocument<?> resource = UnderlyingDocument.of(editor);

		Region data = Region.of(document, event.getOffset(), event.getLength(), event.getText());
		
		send(build(time, resource, data, IdeCodeOperationTypeEnum.PASTE));
	}
	
	private static class PasteHandler extends AbstractCommandHandler
	{
		final AtomicBoolean pasted;
		
		PasteHandler()
		{
			this.pasted = new AtomicBoolean(false);
		}

		public final Object execute(final ExecutionEvent event) throws ExecutionException
		{
			// TODO rm
			if (IdeApplication.getInstance().isDebug()){System.out.println("--pasted-- current value "+this.pasted + " changes to true");}
			
			this.pasted.set(true);
			
			return null;
		}
	}
	
	@Override
	public final void postRegister()
	{
		this.handler.activate(IWorkbenchCommandConstants.EDIT_PASTE);
	}

	@Override
	public final void preUnregister()
	{
		this.handler.deactivate();
	}

	public final void documentAboutToBeChanged(final DocumentEvent event)
	{
	}

	public final void documentChanged(final DocumentEvent event)
	{
		// TODO rm
		if (IdeApplication.getInstance().isDebug()){System.out.println("--pasted-- current value "+this.handler.pasted + " compares to true and sets to false");}

		if (!this.handler.pasted.compareAndSet(true, false))
		{
			// TODO rm
			if (IdeApplication.getInstance().isDebug()){System.out.println("--pasted-- comparison failed");}

			return;
		}
		
		final long time = currentTime();
		
		executor.execute(new Runnable()
		{
			public final void run()
			{
				process(time, event);
			}
		});
	}

	public final void selectionChanged(final IWorkbenchPart part, final ITextSelection selection)
	{
		final long time = currentTime();
		
		executor.execute(new Runnable()
		{
			public final void run()
			{
				process(time, part, selection);
			}
		});
	}
}
