package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;

public class PerspectiveAdapter extends Adapter implements PerspectiveListener
{
	public void perspectiveOpened(IWorkbenchPage page, IPerspectiveDescriptor perspective)
	{
	}

	public void perspectiveClosed(IWorkbenchPage page, IPerspectiveDescriptor perspective)
	{
	}

	public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective)
	{
	}

	public void perspectiveDeactivated(IWorkbenchPage page, IPerspectiveDescriptor perspective)
	{
	}

	public void perspectivePreDeactivate(IWorkbenchPage page, IPerspectiveDescriptor perspective)
	{
	}

	public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, IWorkbenchPartReference part, String change)
	{
	}

	public void perspectiveChanged(IWorkbenchPage page, IPerspectiveDescriptor perspective, String change)
	{
	}

	public void perspectiveSavedAs(IWorkbenchPage page, IPerspectiveDescriptor before, IPerspectiveDescriptor after)
	{
	}
}
