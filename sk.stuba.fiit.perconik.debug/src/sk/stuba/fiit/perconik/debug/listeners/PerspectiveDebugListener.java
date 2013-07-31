package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.listeners.PerspectiveListener;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class PerspectiveDebugListener extends AbstractDebugListener implements PerspectiveListener
{
	public PerspectiveDebugListener()
	{
	}
	
	public PerspectiveDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void perspectiveOpened(final IWorkbenchPage page, final IPerspectiveDescriptor perspective)
	{
		// TODO
	}

	public final void perspectiveClosed(final IWorkbenchPage page, final IPerspectiveDescriptor perspective)
	{
		// TODO
	}

	public final void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor perspective)
	{
		// TODO
	}

	public final void perspectiveDeactivated(final IWorkbenchPage page, final IPerspectiveDescriptor perspective)
	{
		// TODO
	}

	public final void perspectivePreDeactivate(final IWorkbenchPage page, final IPerspectiveDescriptor perspective)
	{
		// TODO
	}

	public final void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor perspective, final IWorkbenchPartReference part, String change)
	{
		// TODO
	}

	public final void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor perspective, final String change)
	{
		// TODO
	}

	public final void perspectiveSavedAs(final IWorkbenchPage page, final IPerspectiveDescriptor before, final IPerspectiveDescriptor after)
	{
		// TODO
	}
}
