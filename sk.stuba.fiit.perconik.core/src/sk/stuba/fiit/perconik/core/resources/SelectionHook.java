package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

final class SelectionHook extends InternalHook<IWorkbenchWindow, SelectionListener> implements WindowListener
{
	SelectionHook(final SelectionListener listener)
	{
		super(new WindowHandler(listener));
	}
	
	static final class Support extends AbstractHookSupport<SelectionHook, IWorkbenchWindow, SelectionListener>
	{
		public final Hook<IWorkbenchWindow, SelectionListener> create(final SelectionListener listener)
		{
			return new SelectionHook(listener);
		}
	}

	private static final class WindowHandler extends InternalHandler<IWorkbenchWindow, SelectionListener>
	{
		WindowHandler(final SelectionListener listener)
		{
			super(IWorkbenchWindow.class, listener);
		}

		public final void register(final IWorkbenchWindow window)
		{
			window.getSelectionService().addSelectionListener(this.listener);
		}

		public final void unregister(final IWorkbenchWindow window)
		{
			window.getSelectionService().removeSelectionListener(this.listener);
		}
	}
	
	@Override
	final void preRegisterInternal()
	{
		Hooks.addWindowsAsynchronouslyTo(this);
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