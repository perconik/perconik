package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbenchWindow;
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
public final class WindowListener extends Listener implements sk.stuba.fiit.perconik.core.listeners.WindowListener
{
	public WindowListener()
	{
	}

	public void windowOpened(IWorkbenchWindow window)
	{
	}

	public void windowClosed(IWorkbenchWindow window)
	{
	}

	public void windowActivated(IWorkbenchWindow window)
	{
	}

	public void windowDeactivated(IWorkbenchWindow window)
	{
	}
}
