package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;

enum TextSelectionHandler implements Handler<TextSelectionListener>
{
	INSTANCE;
	
	private static final class SelectionFilter extends InternalFilter<TextSelectionListener> implements SelectionListener
	{
		public SelectionFilter(final TextSelectionListener listener)
		{
			super(listener);
		}

		public final void selectionChanged(final IWorkbenchPart part, final ISelection selection)
		{
			if (selection instanceof TextSelection)
			{
				this.listener.selectionChanged(part, (ITextSelection) selection);
			}
		}
	}
	
	public final void register(final TextSelectionListener listener)
	{
		SelectionHandler.INSTANCE.register(new SelectionFilter(listener));
	}

	public final void unregister(final TextSelectionListener listener)
	{
		SelectionHandler.INSTANCE.unregister(new SelectionFilter(listener));
	}
}
