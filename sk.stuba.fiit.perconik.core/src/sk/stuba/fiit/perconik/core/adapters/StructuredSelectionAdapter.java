package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;

/**
 * An abstract adapter class for a {@code StructuredSelectionListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code StructuredSelectionListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see StructuredSelectionListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class StructuredSelectionAdapter extends Adapter implements StructuredSelectionListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected StructuredSelectionAdapter()
	{
	}

	public void selectionChanged(IWorkbenchPart part, IStructuredSelection selection)
	{
	}
}
