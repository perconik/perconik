package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IWorkbenchPart;
import sk.stuba.fiit.perconik.core.listeners.TextSelectionListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class TextSelectionDebugListener extends AbstractDebugListener implements TextSelectionListener
{
	public TextSelectionDebugListener()
	{
	}
	
	public TextSelectionDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void selectionChanged(final IWorkbenchPart part, final ITextSelection selection)
	{
		this.printHeader("Text selection changed");
		this.printTextSelection(selection);
	}

	private final void printTextSelection(final ITextSelection selection)
	{
		this.put(Debug.dumpTextSelection(selection));
	}
}
