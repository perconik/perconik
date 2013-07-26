package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.core.utilities.SmartStringBuilder;

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
		this.print("Selection changed:");
		this.printSelection(selection);
	}

	private final void printSelection(final ISelection selection)
	{
		this.put(dumpSelection(selection));
	}
	
	static final String dumpSelection(final ISelection selection)
	{
		SmartStringBuilder builder = new SmartStringBuilder().tab();

		

		return builder.toString();
	}
}
