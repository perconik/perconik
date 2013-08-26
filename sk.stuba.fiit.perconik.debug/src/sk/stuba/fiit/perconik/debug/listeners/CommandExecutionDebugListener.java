package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.commands.common.NotDefinedException;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class CommandExecutionDebugListener extends AbstractDebugListener implements CommandExecutionListener
{
	public CommandExecutionDebugListener()
	{
	}
	
	public CommandExecutionDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void preExecute(final String id, final ExecutionEvent event)
	{
		this.printHeader("Command pre execute");
		this.printLine("identifier", id);
		this.printExecutionEvent(event);
	}

	public final void postExecuteSuccess(final String id, final Object result)
	{
		this.printHeader("Command execute success");
		this.printLine("identifier", id);
		this.printLine("result", result);
	}

	public final void postExecuteFailure(final String id, final ExecutionException exception)
	{
		this.printHeader("Command execute failure");
		this.printLine("identifier", id);
		this.printLine("exception", exception);
	}

	public final void notDefined(final String id, final NotDefinedException exception)
	{
		this.printHeader("Command not defined");
		this.printLine("identifier", id);
		this.printLine("exception", exception);
	}

	public final void notEnabled(final String id, final NotEnabledException exception)
	{
		this.printHeader("Command not enabled");
		this.printLine("identifier", id);
		this.printLine("exception", exception);
	}

	public final void notHandled(final String id, final NotHandledException exception)
	{
		this.printHeader("Command not handled");
		this.printLine("identifier", id);
		this.printLine("exception", exception);
	}
	
	private final void printExecutionEvent(final ExecutionEvent event)
	{
		try
		{
			this.put(Debug.dumpExecutionEvent(event));
		}
		catch (CommandException e)
		{
			error("Command error", e);
		}
	}
}
