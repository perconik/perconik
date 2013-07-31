package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

enum WindowHandler implements Handler<WindowListener>
{
	INSTANCE;
	
	public final void add(final WindowListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.getWorkbench().addWindowListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void remove(final WindowListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.getWorkbench().removeWindowListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
