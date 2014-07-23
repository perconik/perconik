package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;

public final class LaunchConfigurationDebugListener extends AbstractDebugListener implements LaunchConfigurationListener
{
	public LaunchConfigurationDebugListener()
	{
	}
	
	public LaunchConfigurationDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void launchConfigurationAdded(final ILaunchConfiguration configuration)
	{
		this.printHeader("Launch added");
		this.printLaunchConfiguration(configuration);
	}

	public final void launchConfigurationRemoved(final ILaunchConfiguration configuration)
	{
		this.printHeader("Launch removed");
		this.printLaunchConfiguration(configuration);
	}

	public final void launchConfigurationChanged(final ILaunchConfiguration configuration)
	{
		this.printHeader("Launch changed");
		this.printLaunchConfiguration(configuration);
	}
	
	private final void printLaunchConfiguration(final ILaunchConfiguration configuration)
	{
		try
		{
			this.put(Debug.dumpLaunchConfiguration(configuration));
		}
		catch (CoreException e)
		{
			error("Launch configuration error", e);
		}
	}	
}
