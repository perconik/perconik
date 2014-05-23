package com.gratex.perconik.activity.ide.listeners;

import static com.google.common.base.Preconditions.checkArgument;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setApplicationData;
import static com.gratex.perconik.activity.ide.IdeDataTransferObjects.setEventData;
import static com.gratex.perconik.activity.ide.listeners.IdeCodeListener.Operation.COPY;
import static com.gratex.perconik.activity.ide.listeners.IdeCodeListener.Operation.CUT;
import static com.gratex.perconik.activity.ide.listeners.IdeCodeListener.Operation.PASTE;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.DISABLED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.EXECUTING;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.FAILED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.SUCCEEDED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.UNDEFINED;
import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.UNHANDLED;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;
import sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionStateHandler;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;
import sk.stuba.fiit.perconik.utilities.MoreArrays;

import com.google.common.base.Throwables;
import com.gratex.perconik.activity.ide.IdeCodeEventType;
import com.gratex.perconik.activity.ide.UacaProxy;
import com.gratex.perconik.services.uaca.ide.dto.*;

/**
 * A listener of {@code IdeCodeOperation} events. This listener creates
 * {@link IdeCodeOperationDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p>Code operation types that this listener is interested in are
 * determined by the {@link IdeCodeOperationTypeEnum} enumeration:
 * 
 * <ul>
 *   <li>Copy - a code is copied.
 *   <li>Cut - a code is cut.
 *   <li>Paste - a code is pasted.
 *   <li>Paste from web - unused, inferred by UACA from regular paste.
 *   <li>Selection changed - a code is selected, cursor is moved discarding
 *   current selection or the code selection is changed otherwise.
 * </ul>
 * 
 * <p>Data available in an {@code IdeCodeOperationDto}:
 * 
 * <ul>
 *   <li>{@code code} - related code.
 *   <li>{@code document} - related document, see documentation of
 *   {@code IdeDocumentDto} in {@link IdeDocumentListener} for more details.
 *   <li>{@code endColumnIndex} - zero based end position
 *   of code on document line.
 *   <li>{@code endRowIndex} - zero based end line number
 *   of code in document.
 *   <li>{@code operationType} - see {@link IdeCodeOperationTypeEnum}
 *   for all possible values in this field.
 *   <li>{@code startColumnIndex} - zero based start position
 *   of code on document line.
 *   <li>{@code startRowIndex} - zero based start line number
 *   of code in document.
 *   <li>{@code webUrl} - unused, inferred by UACA.
 *   <li>See {@link IdeListener} for documentation of inherited data.
 * </ul>
 * 
 * <p>Note that row and column offsets in documents start from zero
 * instead of one.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class IdeCodeListener extends IdeListener implements CommandExecutionListener, DocumentListener, TextSelectionListener
{
	private final CommandExecutionStateHandler paste;
	
	public IdeCodeListener()
	{
		this.paste = CommandExecutionStateHandler.of(PASTE.getIdentifier());
	}

	static enum Operation
	{
		COPY("org.eclipse.ui.edit.copy", IdeCodeEventType.COPY),
		
		CUT("org.eclipse.ui.edit.cut", IdeCodeEventType.CUT),
		
		PASTE("org.eclipse.ui.edit.paste", IdeCodeEventType.PASTE);
		
		private final String id;
		private final IdeCodeEventType ideCodeEventType;
		
		private Operation(String id, IdeCodeEventType ideCodeEventType)
		{
			assert !id.isEmpty();
			
			this.id = id;
			this.ideCodeEventType = ideCodeEventType;
		}
		
		public static final Operation resolve(String id)
		{
			checkArgument(!id.isEmpty());
			
			for (Operation operation: values())
			{
				if (operation.id.equals(id))
				{
					return operation;
				}
			}
			
			return null;
		}
		
		public final String getIdentifier()
		{
			return this.id;
		}
		public final IdeCodeEventType getIdeCodeEventType()
		{
			return this.ideCodeEventType;
		}
	}
	
	static final class Region
	{
		final Position start = new Position();
		
		final Position end = new Position();
		
		String text;
		
		Region()
		{
		}

		static final class Position
		{
			int line, offset;
		}
		
		static final Region of(final IDocument document, int offset, int length, final String text)
		{
			checkArgument(offset >= 0);
			checkArgument(length >= 0);
			checkArgument(text != null);
			
			Region data = new Region();
			
			try
			{
				data.start.line = document.getLineOfOffset(offset);
				data.end.line   = document.getLineOfOffset(offset + length);

				data.start.offset = offset - document.getLineOffset(data.start.line);
				data.end.offset   = offset + length - document.getLineOffset(data.end.line);
				
				String delimeter = document.getLineDelimiter(data.end.line);
				
				if (delimeter != null && text.endsWith(delimeter))
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

	static final IdeCodeEventRequest build(final long time, final UnderlyingDocument<?> document, final Region region)
	{
		final IdeCodeEventRequest data = new IdeCodeEventRequest();

		data.setText(region.text);
		
		data.setStartColumnIndex(region.start.offset);
		data.setStartRowIndex(region.start.line);
		
		data.setEndColumnIndex(region.end.offset);
		data.setEndRowIndex(region.end.line);

		if (Log.enabled())
		{
//			Log.message().appendln("document: " + document.getPath() + " operation: " + type)
			Log.message().appendln("document: " + document.getPath())
			.append("text: '" + region.text + "' ")
			.append("from " + region.start.line + ":" + region.start.offset + " ")
			.appendln("to " + region.end.line + ":" + region.end.offset).appendTo(console);
		}
		
		document.setDocumentData(data);
		document.setProjectData(data);
		
		setApplicationData(data);
		setEventData(data, time);

		return data;
	}
	
	static final void process(final long time, final Operation operation)
	{
		Clipboard clipboard = new Clipboard(Workbenches.getActiveWindow().getShell().getDisplay());
		
	    if (!MoreArrays.contains(clipboard.getAvailableTypeNames(), "Rich Text Format"))
	    {
	    	if (Log.enabled()) Log.message().appendln("copy / cut: not rich text format").appendTo(console);
	    	
	    	return;
	    }
	    
	    String text = clipboard.getContents(TextTransfer.getInstance()).toString();
	    
	    clipboard.dispose();
		
		IEditorPart editor = Editors.getActiveEditor();
		
		if (editor == null)
		{
			if (Log.enabled()) Log.message().appendln("copy / cut: no active editor not found").appendTo(console);
	
			return;
		}
		
		ISourceViewer viewer   = Editors.getSourceViewer(editor);
		IDocument     document = viewer.getDocument();
		
		UnderlyingDocument<?> resource = UnderlyingDocument.from(editor);
		
		Point range = viewer.getSelectedRange();
		
		int offset = range.x;
		int length = range.y;
		
		Region data = Region.of(document, offset, length, text);

		String selection;
		
		try
		{
			selection = document.get(offset, length);
		}
		catch (BadLocationException e)
		{
			throw Throwables.propagate(e);
		}

		if (operation == COPY && !data.text.equals(selection))
		{
			if (Log.enabled())
			{
				Log.message().append("copy: clipboard content not equal to editor selection")
				.append(" '").append(data.text).append("' != '").append(selection).appendln("'")
				.appendTo(console);
			}

			return;
		}
		else if (operation == CUT && !selection.isEmpty())
		{
			if (Log.enabled())
			{
				Log.message().append("cut: editor selection not empty")
				.append(" '").append(selection).appendln("'")
				.appendTo(console);
			}

			return;
		}
		
		UacaProxy.sendCodeEvent(build(time, resource, data), operation.getIdeCodeEventType());
	}

	static final void process(final long time, final DocumentEvent event)
	{
		IDocument   document = event.getDocument();
		IEditorPart editor   = Editors.forDocument(document);
		
		if (editor == null)
		{
			if (Log.enabled()) Log.message().appendln("paste: editor not found / documents not equal").appendTo(console);
	
			return;
		}
		
		UnderlyingDocument<?> resource = UnderlyingDocument.from(editor);
	
		Region data = Region.of(document, event.getOffset(), event.getLength(), event.getText());
		
		UacaProxy.sendCodeEvent(build(time, resource, data), IdeCodeEventType.PASTE);
	}

	static final void process(final long time, final IWorkbenchPart part, final ITextSelection selection)
	{
		if (!(part instanceof IEditorPart))
		{
			return;
		}
		
		IEditorPart editor   = (IEditorPart) part;
		IDocument   document = Editors.getDocument(editor);
		
		UnderlyingDocument<?> resource = UnderlyingDocument.from(editor);
		
		if (document == null || resource == null)
		{
			return;
		}

		Region data = Region.of(document, selection.getOffset(), selection.getLength(), selection.getText());
		
		assert data.start.line == selection.getStartLine();
		assert data.end.line   == selection.getEndLine();
		
		UacaProxy.sendCodeEvent(build(time, resource, data), IdeCodeEventType.SELECTION_CHANGED);
	}
	
	public final void documentAboutToBeChanged(final DocumentEvent event)
	{
	}

	public final void documentChanged(final DocumentEvent event)
	{
		if (Log.enabled()) Log.message().appendln("paste: " + this.paste.getState()).appendTo(console);

		if (this.paste.getState() != EXECUTING)
		{
			if (Log.enabled()) Log.message().appendln("paste: comparison failed -> not executing").appendTo(console);

			return;
		}
		
		final long time = Utilities.currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				process(time, event);
			}
		});
	}

	public final void selectionChanged(final IWorkbenchPart part, final ITextSelection selection)
	{
		final long time = Utilities.currentTime();
		
		execute(new Runnable()
		{
			public final void run()
			{
				process(time, part, selection);
			}
		});
	}

	public final void preExecute(final String id, final ExecutionEvent event)
	{
		this.paste.transitOnMatch(id, EXECUTING);
	}

	public final void postExecuteSuccess(final String id, final Object result)
	{
		final Operation operation = Operation.resolve(id);
		
		if (operation == COPY || operation == CUT)
		{
			final long time = Utilities.currentTime();
			
			executeSafely(new Runnable()
			{
				public final void run()
				{
					process(time, operation);
				}
			});
		}
		else if (operation == PASTE)
		{
			this.paste.transit(SUCCEEDED);
		}
	}

	public final void postExecuteFailure(final String id, final ExecutionException exception)
	{
		this.paste.transitOnMatch(id, FAILED);
	}

	public final void notDefined(final String id, final NotDefinedException exception)
	{
		this.paste.transitOnMatch(id, UNDEFINED);
	}

	public final void notEnabled(final String id, final NotEnabledException exception)
	{
		this.paste.transitOnMatch(id, DISABLED);
	}

	public final void notHandled(final String id, final NotHandledException exception)
	{
		this.paste.transitOnMatch(id, UNHANDLED);
	}
}
