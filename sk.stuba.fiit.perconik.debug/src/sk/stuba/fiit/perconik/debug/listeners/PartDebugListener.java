package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class PartDebugListener extends AbstractDebugListener implements PartListener
{
	public PartDebugListener()
	{
	}
	
	public PartDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void partOpened(final IWorkbenchPart part)
	{
	}

	public final void partClosed(final IWorkbenchPart part)
	{
	}

	public final void partActivated(final IWorkbenchPart part)
	{
	}

	public final void partDeactivated(final IWorkbenchPart part)
	{
	}

	public final void partBroughtToTop(final IWorkbenchPart part)
	{
	}
}
