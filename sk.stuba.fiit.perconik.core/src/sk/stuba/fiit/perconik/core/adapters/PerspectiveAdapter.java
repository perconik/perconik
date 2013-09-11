package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;

/**
 * An abstract adapter class for a {@code PerspectiveListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code PerspectiveListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see PerspectiveListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class PerspectiveAdapter extends Adapter implements PerspectiveListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected PerspectiveAdapter()
	{
	}

	public void perspectiveOpened(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public void perspectiveClosed(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public void perspectiveDeactivated(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public void perspectivePreDeactivate(IWorkbenchPage page, IPerspectiveDescriptor descriptor)
	{
	}

	public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor descriptor, String change)
	{
	}

	public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor descriptor, IWorkbenchPartReference reference, String change)
	{
	}

	public void perspectiveSavedAs(IWorkbenchPage page, IPerspectiveDescriptor before, IPerspectiveDescriptor after)
	{
	}
}
