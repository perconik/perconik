package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.debug.core.DebugEvent;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;
import sk.stuba.fiit.perconik.core.listeners.OperationHistoryListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class DebugEventsDebugListener extends AbstractDebugListener implements DebugEventsListener
{
	public DebugEventsDebugListener()
	{
	}
	
	public DebugEventsDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void handleDebugEvents(final DebugEvent[] events)
	{
		this.printHeader("Debug events");
		this.printDebugEvents(events);
	}
	
	private final void printDebugEvents(final DebugEvent[] events)
	{
		this.put(Debug.dumpDebugEvents(events));
	}
}
