package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;

import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

enum WorkbenchHandler implements Handler<WorkbenchListener>
{
	INSTANCE;
	
	public final void register(final WorkbenchListener listener)
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

	public final void unregister(final WorkbenchListener listener)
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
