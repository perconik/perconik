package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.MarkSelectionListener;

/**
 * An abstract adapter class for a {@code MarkSelectionListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code MarkSelectionListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see MarkSelectionListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class MarkSelectionAdapter extends Adapter implements MarkSelectionListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected MarkSelectionAdapter()
	{
	}

	public void selectionChanged(IWorkbenchPart part, IMarkSelection selection)
	{
	}
}
