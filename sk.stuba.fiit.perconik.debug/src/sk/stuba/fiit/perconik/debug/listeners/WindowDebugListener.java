package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class WindowDebugListener extends AbstractDebugListener implements WindowListener
{
	public WindowDebugListener()
	{
	}
	
	public WindowDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void windowOpened(final IWorkbenchWindow window)
	{
	}

	public final void windowClosed(final IWorkbenchWindow window)
	{
	}

	public final void windowActivated(final IWorkbenchWindow window)
	{
	}

	public final void windowDeactivated(final IWorkbenchWindow window)
	{
	}
}
