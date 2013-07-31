package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.listeners.WindowListener;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

//TODO check if listener works after active window changes, probably not

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
				Workbenches.waitForWorkbench().addWindowListener(listener);
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
				Workbenches.waitForWorkbench().removeWindowListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
