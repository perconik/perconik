package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

final class PageHook extends InternalHook<IWorkbenchWindow, PageListener> implements WindowListener
{
	PageHook(final PageListener listener)
	{
		super(new InternalWindowHandler(listener));
	}
	
	static final class Support extends AbstractHookSupport<PageHook, IWorkbenchWindow, PageListener>
	{
		public final Hook<IWorkbenchWindow, PageListener> create(final PageListener listener)
		{
			return new PageHook(listener);
		}
	}

	private static final class InternalWindowHandler extends InternalHandler<IWorkbenchWindow, PageListener>
	{
		InternalWindowHandler(final PageListener listener)
		{
			super(listener);
		}

		public final void register(final IWorkbenchWindow window)
		{
			window.addPageListener(this.listener);
		}

		public final void unregister(final IWorkbenchWindow window)
		{
			window.removePageListener(this.listener);
		}
	}
	
	@Override
	final void preRegisterInternal()
	{
		Hooks.addWindowsSynchronouslyTo(this);
	}
	
	public final void windowOpened(final IWorkbenchWindow window)
	{
		this.add(window);
	}

	public final void windowClosed(final IWorkbenchWindow window)
	{
		this.remove(window);
	}

	public final void windowActivated(final IWorkbenchWindow window)
	{
	}

	public final void windowDeactivated(final IWorkbenchWindow window)
	{
	}
}