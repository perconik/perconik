package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.CommandEvent;
import org.eclipse.core.commands.common.CommandException;
import sk.stuba.fiit.perconik.core.listeners.CommandListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class CommandDebugListener extends AbstractDebugListener implements CommandListener
{
	public CommandDebugListener()
	{
	}
	
	public CommandDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void commandChanged(final CommandEvent event)
	{
		this.printHeader("Command changed");
		this.printCommandEvent(event);
	}
	
	private final void printCommandEvent(final CommandEvent event)
	{
		try
		{
			this.put(Debug.dumpCommandEvent(event));
		}
		catch (CommandException e)
		{
			error("Command error", e);
		}
	}
}
