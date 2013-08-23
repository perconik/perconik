package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.core.commands.Command;
import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.listeners.CommandListener;
import sk.stuba.fiit.perconik.eclipse.core.commands.Commands;

enum CommandHandler implements Handler<CommandListener>
{
	INSTANCE;
	
	public final void register(final CommandListener listener)
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

	public final void unregister(final CommandListener listener)
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
