package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbench;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class WorkbenchDebugListener extends AbstractDebugListener implements WorkbenchListener
{
	public WorkbenchDebugListener()
	{
	}
	
	public WorkbenchDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final boolean preShutdown(final IWorkbench workbench, final boolean forced)
	{
		return forced;
	}

	public final void postShutdown(final IWorkbench workbench)
	{
	}
}
