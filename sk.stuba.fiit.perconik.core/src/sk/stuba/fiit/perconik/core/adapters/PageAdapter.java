package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IWorkbenchPage;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.PageListener;

/**
 * An abstract adapter class for a {@code PageListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code PageListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see PageListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class PageAdapter extends Adapter implements PageListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected PageAdapter()
	{
	}

	public void pageOpened(IWorkbenchPage page)
	{
	}

	public void pageClosed(IWorkbenchPage page)
	{
	}

	public void pageActivated(IWorkbenchPage page)
	{
	}
}
