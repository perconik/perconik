package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;

public class CommandExecutionAdapter extends Adapter implements CommandExecutionListener
{
	public void preExecute(String id, ExecutionEvent event)
	{
	}

	public void postExecuteSuccess(String id, Object result)
	{
	}

	public void postExecuteFailure(String id, ExecutionException exception)
	{
	}

	public void notDefined(String id, NotDefinedException exception)
	{
	}

	public void notEnabled(String id, NotEnabledException exception)
	{
	}

	public void notHandled(String id, NotHandledException exception)
	{
	}
}
