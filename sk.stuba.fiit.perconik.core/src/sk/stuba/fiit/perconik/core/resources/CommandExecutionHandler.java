package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.commands.ICommandService;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

enum CommandExecutionHandler implements Handler<CommandExecutionListener>
{
	INSTANCE;
	
	public final void register(final CommandExecutionListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				IWorkbench      workbench = Workbenches.waitForWorkbench();
				ICommandService service   = (ICommandService) workbench.getAdapter(ICommandService.class);
				
				service.addExecutionListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void unregister(final CommandExecutionListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				IWorkbench      workbench = Workbenches.waitForWorkbench();
				ICommandService service   = (ICommandService) workbench.getAdapter(ICommandService.class);
				
				service.removeExecutionListener(listener);
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
