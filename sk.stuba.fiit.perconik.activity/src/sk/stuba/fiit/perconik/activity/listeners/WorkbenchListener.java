package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbench;
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
public final class WorkbenchListener extends Listener implements sk.stuba.fiit.perconik.core.listeners.WorkbenchListener
{
	public WorkbenchListener()
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
