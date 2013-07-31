package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class CommandExecutionDebugListener extends AbstractDebugListener implements CommandExecutionListener
{
	public CommandExecutionDebugListener()
	{
	}
	
	public CommandExecutionDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void preExecute(final String id, final ExecutionEvent event)
	{
		// TODO
	}

	public final void postExecuteSuccess(final String id, final Object result)
	{
		// TODO
	}

	public final void postExecuteFailure(final String id, final ExecutionException exception)
	{
		// TODO
	}

	public final void notDefined(final String id, final NotDefinedException exception)
	{
		// TODO
	}

	public final void notEnabled(final String id, final NotEnabledException exception)
	{
		// TODO
	}

	public final void notHandled(final String id, final NotHandledException exception)
	{
		// TODO
	}
}
