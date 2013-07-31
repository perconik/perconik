package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.debug.core.DebugPlugin;

enum DebugEventSetHandler implements Handler<DebugEventSetListener>
{
	INSTANCE;
	
	public final void add(final DebugEventSetListener listener)
	{
		DebugPlugin.getDefault().addDebugEventListener(listener);
	}

	public final void remove(final DebugEventSetListener listener)
	{
		DebugPlugin.getDefault().removeDebugEventListener(listener);
	}
}
