package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.debug.core.DebugPlugin;

enum LaunchesHandler implements Handler<LaunchesListener>
{
	INSTANCE;
	
	public final void add(final LaunchesListener listener)
	{
		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(listener);
	}

	public final void remove(final LaunchesListener listener)
	{
		DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(listener);
	}
}
