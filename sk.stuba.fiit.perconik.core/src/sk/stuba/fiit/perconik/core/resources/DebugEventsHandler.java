package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.debug.core.DebugPlugin;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;

enum DebugEventsHandler implements Handler<DebugEventsListener>
{
	INSTANCE;
	
	public final void add(final DebugEventsListener listener)
	{
		DebugPlugin.getDefault().addDebugEventListener(listener);
	}

	public final void remove(final DebugEventsListener listener)
	{
		DebugPlugin.getDefault().removeDebugEventListener(listener);
	}
}
