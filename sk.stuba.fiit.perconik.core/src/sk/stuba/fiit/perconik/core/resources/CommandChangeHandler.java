package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.core.commands.Command;
import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.listeners.CommandChangeListener;
import sk.stuba.fiit.perconik.eclipse.core.commands.Commands;

enum CommandChangeHandler implements Handler<CommandChangeListener>
{
	INSTANCE;
	
	public final void register(final CommandChangeListener listener)
	{
		final Runnable addListener = new Runnable()
		{
			@Override
			public final void run()
			{
				for (Command command: Commands.waitForCommandService().getDefinedCommands())
				{
					command.addCommandListener(listener);
				}
			}
		};
	
		Display.getDefault().asyncExec(addListener);
	}

	public final void unregister(final CommandChangeListener listener)
	{
		final Runnable removeListener = new Runnable()
		{
			@Override
			public final void run()
			{
				for (Command command: Commands.waitForCommandService().getDefinedCommands())
				{
					command.removeCommandListener(listener);
				}
			}
		};
	
		Display.getDefault().asyncExec(removeListener);
	}
}
