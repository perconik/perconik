package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.MarkSelectionListener;

public final class MarkSelectionDebugListener extends AbstractDebugListener implements MarkSelectionListener
{
	public MarkSelectionDebugListener()
	{
	}
	
	public MarkSelectionDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void selectionChanged(final IWorkbenchPart part, final IMarkSelection selection)
	{
		this.printHeader("Mark selection changed");
		this.printMarkSelection(selection);
	}

	private final void printMarkSelection(final IMarkSelection selection)
	{
		this.put(Debug.dumpMarkSelection(selection));
	}
}
