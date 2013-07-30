package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbench;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.debug.Debug;

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
		this.printHeader("Workbench pre shutdown");
		this.printWorkbench(workbench);

		return forced;
	}

	public final void postShutdown(final IWorkbench workbench)
	{
		this.printHeader("Workbench post shutdown");
		this.printWorkbench(workbench);
	}
	
	private final void printWorkbench(final IWorkbench workbench)
	{
		this.put(Debug.dumpWorkbench(workbench));
	}

}
