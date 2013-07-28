package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ui.IWorkbenchPage;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.debug.Debug;

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
		this.print("Page opened:");
		this.printPage(page);
	}

	public final void pageClosed(final IWorkbenchPage page)
	{
		this.print("Page closed:");
		this.printPage(page);
	}

	public final void pageActivated(final IWorkbenchPage page)
	{
		this.print("Page activated:");
		this.printPage(page);
	}
	
	private final void printPage(final IWorkbenchPage page)
	{
		this.put(Debug.dumpPage(page));
	}
}
