package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import sk.stuba.fiit.perconik.core.listeners.DocumentChangeListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

enum DocumentChangeHandler implements Handler<DocumentChangeListener>
{
	INSTANCE;
	
	public final void register(final DocumentChangeListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				IEditorPart editor = Editors.waitForActiveEditor();
				
				Editors.getDocument(editor).addDocumentListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void unregister(final DocumentChangeListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				IEditorPart editor = Editors.waitForActiveEditor();
				
				Editors.getDocument(editor).removeDocumentListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
