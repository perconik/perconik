package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IWorkbench;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;

/**
 * An abstract adapter class for a {@code WorkbenchListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code WorkbenchListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see WorkbenchListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class WorkbenchAdapter extends Adapter implements WorkbenchListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected WorkbenchAdapter()
	{
	}

	public boolean preShutdown(IWorkbench workbench, boolean forced)
	{
		return true;
	}

	public void postShutdown(IWorkbench workbench)
	{
	}
}
