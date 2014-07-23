package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;

/**
 * TODO
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
@Version("0.0.1")
public final class PerspectiveListener extends Listener implements sk.stuba.fiit.perconik.core.listeners.PerspectiveListener
{
	public PerspectiveListener()
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
