package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.listeners.PageListener;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

// TODO check if listener works after active window changes, probably not

enum PageHandler implements Handler<PageListener>
{
	INSTANCE;
	
	public final void add(final PageListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForActiveWindow().addPageListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void remove(final PageListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForActiveWindow().removePageListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
