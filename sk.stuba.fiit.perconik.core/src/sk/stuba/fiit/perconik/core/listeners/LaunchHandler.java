package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.debug.core.DebugPlugin;

enum LaunchHandler implements Handler<LaunchListener>
{
	INSTANCE;
	
	public final void add(final LaunchListener listener)
	{
		DebugPlugin.getDefault().getLaunchManager().addLaunchListener(listener);
	}

	public final void remove(final LaunchListener listener)
	{
		DebugPlugin.getDefault().getLaunchManager().removeLaunchListener(listener);
	}
}
