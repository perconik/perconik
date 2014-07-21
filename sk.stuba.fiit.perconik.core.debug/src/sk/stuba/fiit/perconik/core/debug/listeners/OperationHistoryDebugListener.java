package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;

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
