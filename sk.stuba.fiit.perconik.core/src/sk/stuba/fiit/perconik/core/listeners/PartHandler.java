package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

//TODO check if listener works after active window changes, probably not

enum PartHandler implements Handler<PartListener>
{
	INSTANCE;
	
	public final void add(final PartListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForActivePage().addPartListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void remove(final PartListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForActivePage().removePartListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
