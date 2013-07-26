package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class SelectionDebugListener extends AbstractDebugListener implements SelectionListener
{
	public SelectionDebugListener()
	{
	}
	
	public SelectionDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void selectionChanged(final IWorkbenchPart part, final ISelection selection)
	{
	}
}
