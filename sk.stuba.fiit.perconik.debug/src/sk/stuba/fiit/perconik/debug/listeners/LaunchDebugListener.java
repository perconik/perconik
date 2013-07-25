package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.debug.core.ILaunch;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class LaunchDebugListener extends AbstractDebugListener implements LaunchListener
{
	public LaunchDebugListener()
	{
	}
	
	public LaunchDebugListener(PluginConsole console)
	{
		super(console);
	}
	
	public void launchRemoved(ILaunch launch)
	{
	}

	public void launchAdded(ILaunch launch)
	{
	}

	public void launchChanged(ILaunch launch)
	{
	}
}
