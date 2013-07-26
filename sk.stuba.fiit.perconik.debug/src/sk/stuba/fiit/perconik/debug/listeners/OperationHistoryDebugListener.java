package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.operations.OperationHistoryEvent;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class OperationHistoryDebugListener extends AbstractDebugListener implements OperationHistoryListener
{
	public OperationHistoryDebugListener()
	{
	}
	
	public OperationHistoryDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void historyNotification(final OperationHistoryEvent event)
	{
	}
}
