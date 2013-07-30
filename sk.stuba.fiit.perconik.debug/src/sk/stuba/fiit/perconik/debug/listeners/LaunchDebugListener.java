package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.debug.Debug;

public final class LaunchDebugListener extends AbstractDebugListener implements LaunchListener
{
	public LaunchDebugListener()
	{
	}
	
	public LaunchDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void launchAdded(final ILaunch launch)
	{
		this.printHeader("Launch added");
		this.printLaunch(launch);
	}

	public final void launchRemoved(final ILaunch launch)
	{
		this.printHeader("Launch removed");
		this.printLaunch(launch);
	}

	public final void launchChanged(final ILaunch launch)
	{
		this.printHeader("Launch changed");
		this.printLaunch(launch);
	}
	
	private final void printLaunch(final ILaunch launch)
	{
		try
		{
			this.put(Debug.dumpLaunch(launch));
		}
		catch (CoreException e)
		{
			error("Launch error", e);
		}
	}	
}
