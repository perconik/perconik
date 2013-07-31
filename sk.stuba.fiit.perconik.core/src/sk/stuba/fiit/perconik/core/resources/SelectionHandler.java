package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

//TODO check if listener works after active window changes, probably not

enum SelectionHandler implements Handler<SelectionListener>
{
	INSTANCE;
	
	public final void add(final SelectionListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForActiveWindow().getSelectionService().addSelectionListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void remove(final SelectionListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				Workbenches.waitForActiveWindow().getSelectionService().removeSelectionListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
