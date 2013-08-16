package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.debug.core.DebugEvent;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class DebugEventsDebugListener extends AbstractDebugListener implements DebugEventsListener
{
	public DebugEventsDebugListener()
	{
	}
	
	public DebugEventsDebugListener(final DebugConsole console)
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
