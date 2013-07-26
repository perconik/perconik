package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.debug.core.ILaunchConfiguration;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class LaunchConfigurationDebugListener extends AbstractDebugListener implements LaunchConfigurationListener
{
	public LaunchConfigurationDebugListener()
	{
	}
	
	public LaunchConfigurationDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void launchConfigurationAdded(final ILaunchConfiguration configuration)
	{
	}

	public final void launchConfigurationRemoved(final ILaunchConfiguration configuration)
	{
	}

	public final void launchConfigurationChanged(final ILaunchConfiguration configuration)
	{
	}
}
