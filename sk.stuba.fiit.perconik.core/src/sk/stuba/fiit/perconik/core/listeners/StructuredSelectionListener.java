package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A structured selection listener.
 * 
 * @see Listener 
 * @see SelectionListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface StructuredSelectionListener extends Listener
{
	public void selectionChanged(IWorkbenchPart part, IStructuredSelection selection);
}
