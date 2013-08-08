package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

//TODO check if listener works after active window changes, probably not

enum PartHandler implements Handler<PartListener>
{
	INSTANCE;
	
	public final void register(final PartListener listener)
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

	public final void unregister(final PartListener listener)
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
