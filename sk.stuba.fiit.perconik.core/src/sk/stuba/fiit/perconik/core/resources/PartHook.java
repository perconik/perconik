package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;

final class PartHook extends InternalHook<IWorkbenchWindow, PartListener> implements WindowListener
{
	PartHook(final PartListener listener)
	{
		super(new InternalPageHandler(listener));
	}
	
	static final class Support extends AbstractHookSupport<PartHook, IWorkbenchWindow, PartListener>
	{
		public final Hook<IWorkbenchWindow, PartListener> create(final PartListener listener)
		{
			return new PartHook(listener);
		}
	}

	private static final class InternalPageHandler extends InternalHandler<IWorkbenchWindow, PartListener>
	{
		InternalPageHandler(final PartListener listener)
		{
			super(listener);
		}

		public final void register(final IWorkbenchWindow window)
		{
			System.out.println("ADDING part LISTENER");//TODO
			
			window.getPartService().addPartListener(this.listener);
		}

		public final void unregister(final IWorkbenchWindow window)
		{
			System.out.println("REMOVING part LISTENER");
			
			window.getPartService().removePartListener(this.listener);
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

// TODO
//final class PartHook extends InternalHook<IWorkbenchPage, PartListener> implements PageListener
//{
//	PartHook(final PartListener listener)
//	{
//		super(new InternalPageHandler(listener));
//	}
//	
//	static final class Support extends AbstractHookSupport<PartHook, IWorkbenchPage, PartListener>
//	{
//		public final Hook<IWorkbenchPage, PartListener> create(final PartListener listener)
//		{
//			return new PartHook(listener);
//		}
//	}
//
//	private static final class InternalPageHandler extends InternalHandler<IWorkbenchPage, PartListener>
//	{
//		InternalPageHandler(final PartListener listener)
//		{
//			super(listener);
//		}
//
//		public final void register(final IWorkbenchPage page)
//		{
//			System.out.println("ADDING part LISTENER");//TODO
//			
//			page.addPartListener(this.listener);
//		}
//
//		public final void unregister(final IWorkbenchPage page)
//		{
//			System.out.println("REMOVING part LISTENER");
//			
//			page.removePartListener(this.listener);
//		}
//	}
//	
//	@Override
//	final void preRegisterInternal()
//	{
//		Hooks.addPagesAsynchronouslyTo(this);
//	}
//	
//	public final void pageOpened(final IWorkbenchPage page)
//	{
//		this.add(page);
//	}
//
//	public final void pageClosed(final IWorkbenchPage page)
//	{
//		this.remove(page);
//	}
//
//	public final void pageActivated(final IWorkbenchPage page)
//	{
//	}
//}
