package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

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
				Workbenches.getWorkbench().addWorkbenchListener(listener);
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
				Workbenches.getWorkbench().removeWorkbenchListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
