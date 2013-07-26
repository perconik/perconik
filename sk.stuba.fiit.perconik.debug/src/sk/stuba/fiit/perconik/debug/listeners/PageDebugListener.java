package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbenchPage;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class PageDebugListener extends AbstractDebugListener implements PageListener
{
	public PageDebugListener()
	{
	}
	
	public PageDebugListener(final PluginConsole console)
	{
		super(console);
	}

	public final void pageOpened(final IWorkbenchPage page)
	{
	}

	public final void pageClosed(final IWorkbenchPage page)
	{
	}

	public final void pageActivated(final IWorkbenchPage page)
	{
	}
}
