package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;

enum StructuredSelectionHandler implements Handler<StructuredSelectionListener>
{
	INSTANCE;
	
	private static final class SelectionFilter extends InternalFilter<StructuredSelectionListener> implements SelectionListener
	{
		public SelectionFilter(final StructuredSelectionListener listener)
		{
			super(listener);
		}

		public final void selectionChanged(final IWorkbenchPart part, final ISelection selection)
		{
			if (selection instanceof StructuredSelection)
			{
				this.listener.selectionChanged(part, (IStructuredSelection) selection);
			}
		}
	}
	
	public final void register(final StructuredSelectionListener listener)
	{
		SelectionHandler.INSTANCE.register(new SelectionFilter(listener));
	}

	public final void unregister(final StructuredSelectionListener listener)
	{
		SelectionHandler.INSTANCE.unregister(new SelectionFilter(listener));
	}
}
