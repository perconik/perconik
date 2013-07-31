package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.debug.core.DebugPlugin;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;

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
