package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class WindowDebugListener extends AbstractDebugListener implements WindowListener
{
	public WindowDebugListener()
	{
	}
	
	public WindowDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void windowOpened(final IWorkbenchWindow window)
	{
		this.printHeader("Window opened");
		this.printWindow(window);
	}

	public final void windowClosed(final IWorkbenchWindow window)
	{
		this.printHeader("Window closed");
		this.printWindow(window);
	}

	public final void windowActivated(final IWorkbenchWindow window)
	{
		this.printHeader("Window activated");
		this.printWindow(window);
	}

	public final void windowDeactivated(final IWorkbenchWindow window)
	{
		this.printHeader("Window deactivated");
		this.printWindow(window);
	}
	
	private final void printWindow(final IWorkbenchWindow window)
	{
		this.put(Debug.dumpWindow(window));
	}
}
