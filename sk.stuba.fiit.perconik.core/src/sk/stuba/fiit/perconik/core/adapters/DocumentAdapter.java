package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.text.DocumentEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;

/**
 * An abstract adapter class for a {@code DocumentListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code DocumentListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see DocumentListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class DocumentAdapter extends Adapter implements DocumentListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected DocumentAdapter()
	{
	}

	public void documentAboutToBeChanged(DocumentEvent event)
	{
	}

	public void documentChanged(DocumentEvent event)
	{
	}
}
