package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

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
		this.printHeader("Selection changed");
		this.printSelection(selection);
	}

	private final void printSelection(final ISelection selection)
	{
		this.put(Debug.dumpSelection(selection));
	}
}
