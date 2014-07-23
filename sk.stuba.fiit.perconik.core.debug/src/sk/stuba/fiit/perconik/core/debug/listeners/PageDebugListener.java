package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.ui.IWorkbenchPage;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.PageListener;

public final class PageDebugListener extends AbstractDebugListener implements PageListener
{
	public PageDebugListener()
	{
	}
	
	public PageDebugListener(final DebugConsole console)
	{
		super(console);
	}

	public final void pageOpened(final IWorkbenchPage page)
	{
		this.printHeader("Page opened");
		this.printPage(page);
	}

	public final void pageClosed(final IWorkbenchPage page)
	{
		this.printHeader("Page closed");
		this.printPage(page);
	}

	public final void pageActivated(final IWorkbenchPage page)
	{
		this.printHeader("Page activated");
		this.printPage(page);
	}
	
	private final void printPage(final IWorkbenchPage page)
	{
		this.put(Debug.dumpPage(page));
	}
}
