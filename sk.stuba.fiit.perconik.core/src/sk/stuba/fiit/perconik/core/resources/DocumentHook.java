package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class DocumentHook extends InternalHook<IEditorPart, DocumentListener> implements PartListener
{
	DocumentHook(final DocumentListener listener)
	{
		super(new InternalWindowHandler(listener));
	}
	
	static final class Support extends AbstractHookSupport<DocumentHook, IEditorPart, DocumentListener>
	{
		public final Hook<IEditorPart, DocumentListener> create(final DocumentListener listener)
		{
			return new DocumentHook(listener);
		}
	}

	private static final class InternalWindowHandler extends InternalHandler<IEditorPart, DocumentListener>
	{
		InternalWindowHandler(final DocumentListener listener)
		{
			super(listener);
		}

		public final void register(final IEditorPart editor)
		{
			// TODO refactor, doc can be null, pool should be of docs not editors
			
			IDocument document = Editors.getDocument(editor);
			
			document.addDocumentListener(this.listener);
		}

		public final void unregister(final IEditorPart editor)
		{
			IDocument document = Editors.getDocument(editor);
			
			document.removeDocumentListener(this.listener);
		}
	}
	
	private static final IEditorPart fetch(final IWorkbenchPartReference reference)
	{
		IWorkbenchPart part = reference.getPart(false);
		
		return part instanceof IEditorPart ? (IEditorPart) part : null; 
	}

	@Override
	final void preRegisterInternal()
	{
		Hooks.addEditorsSynchronouslyTo(this);
	}

	public final void partOpened(final IWorkbenchPartReference reference)
	{
		IEditorPart editor = fetch(reference);
		
		if (editor != null)
		{
			this.add(editor);
		}
	}

	public final void partClosed(final IWorkbenchPartReference reference)
	{
		IEditorPart editor = fetch(reference);
		
		if (editor != null)
		{
			this.remove(editor);
		}
	}

	public final void partActivated(final IWorkbenchPartReference reference)
	{
	}

	public final void partDeactivated(final IWorkbenchPartReference reference)
	{
	}

	public final void partVisible(final IWorkbenchPartReference reference)
	{
	}

	public final void partHidden(final IWorkbenchPartReference reference)
	{
	}

	public final void partBroughtToTop(final IWorkbenchPartReference reference)
	{
	}

	public final void partInputChanged(final IWorkbenchPartReference reference)
	{
	}
}