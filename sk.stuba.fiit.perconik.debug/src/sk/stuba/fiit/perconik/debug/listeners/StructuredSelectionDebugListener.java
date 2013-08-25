package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.StructuredSelectionListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class StructuredSelectionDebugListener extends AbstractDebugListener implements StructuredSelectionListener
{
	public StructuredSelectionDebugListener()
	{
	}
	
	public StructuredSelectionDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void selectionChanged(final IWorkbenchPart part, final IStructuredSelection selection)
	{
		this.printHeader("Structured selection changed");
		this.printStructuredSelection(selection);
	}

	private final void printStructuredSelection(final IStructuredSelection selection)
	{
		this.put(Debug.dumpStructuredSelection(selection));
	}
}
