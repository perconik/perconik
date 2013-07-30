package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.debug.core.ILaunch;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class LaunchesDebugListener extends AbstractDebugListener implements LaunchesListener
{
	public LaunchesDebugListener()
	{
	}
	
	public LaunchesDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void launchesAdded(final ILaunch[] launches)
	{
	}

	public final void launchesRemoved(final ILaunch[] launches)
	{
	}

	public final void launchesChanged(final ILaunch[] launches)
	{
	}

	public final void launchesTerminated(final ILaunch[] launches)
	{
	}
}
