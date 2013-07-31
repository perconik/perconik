package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

// TODO refactor workbench fetching
// TODO fix bug when listener is registered on one active window and unregistered on another active window (because active windows changed)
// TODO check if listener works after active window changes, probably not

enum WorkbenchHandler implements Handler<WorkbenchListener>
{
	INSTANCE;
	
	public final void add(final WorkbenchListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForWorkbench().addWorkbenchListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void remove(final WorkbenchListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForWorkbench().removeWorkbenchListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
