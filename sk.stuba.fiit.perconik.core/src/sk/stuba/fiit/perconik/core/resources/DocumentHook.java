package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class DocumentHook extends InternalHook<IDocument, DocumentListener> implements EditorListener
{
	DocumentHook(final DocumentListener listener)
	{
		super(new WindowHandler(listener));
	}

	static final class Support extends AbstractHookSupport<DocumentHook, IDocument, DocumentListener>
	{
		public final Hook<IDocument, DocumentListener> create(final DocumentListener listener)
		{
			return new DocumentHook(listener);
		}
	}

	private static final class WindowHandler extends InternalHandler<IDocument, DocumentListener>
	{
		WindowHandler(final DocumentListener listener)
		{
			super(IDocument.class, listener);
		}

		public final void register(final IDocument document)
		{
			document.addDocumentListener(this.listener);
		}

		public final void unregister(final IDocument document)
		{
			document.removeDocumentListener(this.listener);
		}
	}

	@Override
	final void preRegisterInternal()
	{
		Hooks.addDocumentsAsynchronouslyTo(this);
	}

	public final void editorOpened(final IEditorReference reference)
	{
		Hooks.addNonNull(this, Editors.getDocument(Hooks.dereferenceEditor(reference)));
	}

	public final void editorClosed(final IEditorReference reference)
	{
		Hooks.removeNonNull(this, Editors.getDocument(Hooks.dereferenceEditor(reference)));
	}

	public final void editorActivated(final IEditorReference reference)
	{
	}

	public final void editorDeactivated(final IEditorReference reference)
	{
	}

	public final void editorVisible(final IEditorReference reference)
	{
	}

	public final void editorHidden(final IEditorReference reference)
	{
	}

	public final void editorBroughtToTop(final IEditorReference reference)
	{
	}

	public final void editorInputChanged(final IEditorReference reference)
	{
	}
}