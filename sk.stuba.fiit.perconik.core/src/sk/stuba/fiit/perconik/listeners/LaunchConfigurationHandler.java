package sk.stuba.fiit.perconik.listeners;

import org.eclipse.debug.core.DebugPlugin;


enum LaunchConfigurationHandler implements Handler<LaunchConfigurationListener>
{
	INSTANCE;
	
	public final void add(final LaunchConfigurationListener listener)
	{
		DebugPlugin.getDefault().getLaunchManager().addLaunchConfigurationListener(listener);
	}

	public final void remove(final LaunchConfigurationListener listener)
	{
		DebugPlugin.getDefault().getLaunchManager().removeLaunchConfigurationListener(listener);
	}
}
