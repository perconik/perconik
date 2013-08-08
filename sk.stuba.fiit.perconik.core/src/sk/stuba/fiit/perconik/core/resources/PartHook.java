package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IWorkbenchPage;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;

// TODO fix, does not work properly

final class PartHook extends InternalHook<IWorkbenchPage, PartListener> implements PageListener
{
	PartHook(final PartListener listener)
	{
		super(new InternalPageHandler(listener));
	}
	
	static final class Support extends AbstractHookSupport<PartHook, IWorkbenchPage, PartListener>
	{
		public final Hook<IWorkbenchPage, PartListener> create(final PartListener listener)
		{
			return new PartHook(listener);
		}
	}

	private static final class InternalPageHandler extends InternalHandler<IWorkbenchPage, PartListener>
	{
		InternalPageHandler(final PartListener listener)
		{
			super(listener);
		}

		public final void register(final IWorkbenchPage page)
		{
			page.addPartListener(this.listener);
		}

		public final void unregister(final IWorkbenchPage page)
		{
			page.removePartListener(this.listener);
		}
	}
	
	@Override
	final void preRegisterInternal()
	{
		Hooks.addPagesAsynchronouslyTo(this);
	}
	
	public final void pageOpened(final IWorkbenchPage page)
	{
		this.add(page);
	}

	public final void pageClosed(final IWorkbenchPage page)
	{
		this.remove(page);
	}

	public final void pageActivated(final IWorkbenchPage page)
	{
	}
}