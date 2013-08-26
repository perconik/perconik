package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.operations.OperationHistoryEvent;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class OperationHistoryDebugListener extends AbstractDebugListener implements OperationHistoryListener
{
	public OperationHistoryDebugListener()
	{
	}
	
	public OperationHistoryDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void historyNotification(final OperationHistoryEvent event)
	{
		this.printHeader("Operation history");
		this.printOperationHistoryEvent(event);
	}
	
	private final void printOperationHistoryEvent(final OperationHistoryEvent event)
	{
		this.put(Debug.dumpOperationHistoryEvent(event));
	}
}
