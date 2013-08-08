package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

// TODO rm debug

enum SelectionHandler implements Handler<SelectionListener>
{
	INSTANCE;

	static final class InternalSelectionHook extends InternalHook<IWorkbenchWindow, SelectionListener> implements WindowListener
	{
		InternalSelectionHook(final SelectionListener listener)
		{
			super(null, listener);
		}

		@Override
		final void addInternal(final IWorkbenchWindow window)
		{
			window.getSelectionService().addSelectionListener(this.listener);
		}

		@Override
		final void removeInternal(final IWorkbenchWindow window)
		{
			window.getSelectionService().removeSelectionListener(this.listener);
		}
		
		@Override
		final void preRegisterInternal()
		{
			final Runnable addListener = new Runnable()
			{
				@Override
				public final void run()
				{
					for (IWorkbenchWindow window: Workbenches.waitForWorkbench().getWorkbenchWindows())
					{
						add(window);
					}
				}
			};
		
			Display.getDefault().asyncExec(addListener);
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
	
	private final HookSupport<InternalSelectionHook, SelectionListener> support = new HookSupport<>();
	
	public final void add(final SelectionListener listener)
	{
		this.support.hook(DefaultResources.window, listener);
	}

	public final void remove(final SelectionListener listener)
	{
		this.support.unhook(DefaultResources.window, listener);
	}
	
	// TODO rm
//	private static final class InternalSelectionHook extends WindowAdapter
//	{
//		private final SelectionListener listener;
//		
//		private final Set<IWorkbenchWindow> windows;
//		
//		InternalSelectionHook(final SelectionListener listener)
//		{
//			this.listener = Preconditions.checkNotNull(listener);
//			this.windows  = Sets.newCopyOnWriteArraySet();
//		}
//		
//		final void addTo(final IWorkbenchWindow window)
//		{
//			//Activator.getDefault().getConsole().print("adding listener "+listener+" to window "+window);
//			
//			this.windows.add(window);
//			
//			window.getSelectionService().addSelectionListener(this.listener);
//		}
//
//		final void removeFrom(final IWorkbenchWindow window)
//		{
//			//Activator.getDefault().getConsole().print("removing listener "+listener+" from window "+window);
//			
//			window.getSelectionService().removeSelectionListener(this.listener);
//			
//			this.windows.remove(window);
//		}
//
//		@Override
//		public final void preRegister()
//		{
//			//Activator.getDefault().getConsole().print("PRE REGISTER");
//			
//			final Runnable addListener = new Runnable()
//			{
//				@Override
//				public final void run()
//				{
//					for (IWorkbenchWindow window: Workbenches.waitForWorkbench().getWorkbenchWindows())
//					{
//						addTo(window);
//					}
//				}
//			};
//		
//			Display.getDefault().asyncExec(addListener);
//		}
//
//		@Override
//		public final void postUnregister()
//		{
//			//Activator.getDefault().getConsole().print("POST UNREGISTER");
//			
//			for (IWorkbenchWindow window: this.windows)
//			{
//				removeFrom(window);
//			}
//		}
//
//		@Override
//		public final void windowOpened(final IWorkbenchWindow window)
//		{
//			addTo(window);
//		}
//
//		@Override
//		public final void windowClosed(final IWorkbenchWindow window)
//		{
//			removeFrom(window);
//		}
//	}
//	
//	private final Map<SelectionListener, InternalSelectionHook> hooks = Maps.newConcurrentMap();
//	
//	public final void add(final SelectionListener listener)
//	{
//		InternalSelectionHook hook = this.hooks.get(listener);
//		
//		if (hook == null)
//		{
//			hook = new InternalSelectionHook(listener);
//			
//			this.hooks.put(listener, hook);
//		}
//		
//		DefaultResources.getWindowResource().register(hook);
//	}
//
//	public final void remove(final SelectionListener listener)
//	{
//		InternalSelectionHook hook = this.hooks.get(listener);
//		
//		if (hook != null)
//		{
//			DefaultResources.getWindowResource().unregister(hook);
//		}
//	}
}
