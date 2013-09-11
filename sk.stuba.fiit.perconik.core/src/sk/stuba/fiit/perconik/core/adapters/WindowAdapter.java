package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

/**
 * An abstract adapter class for a {@code WindowListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code WindowListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see WindowListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class WindowAdapter extends Adapter implements WindowListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected WindowAdapter()
	{
	}

	public void windowOpened(IWorkbenchWindow window)
	{
	}

	public void windowClosed(IWorkbenchWindow window)
	{
	}

	public void windowActivated(IWorkbenchWindow window)
	{
	}

	public void windowDeactivated(IWorkbenchWindow window)
	{
	}
}
