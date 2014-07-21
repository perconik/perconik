package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.LaunchesListener;

public final class LaunchesDebugListener extends AbstractDebugListener implements LaunchesListener
{
	public LaunchesDebugListener()
	{
	}
	
	public LaunchesDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void launchesAdded(final ILaunch[] launches)
	{
		this.printHeader("Launches added");
		this.printLaunches(launches);
	}

	public final void launchesRemoved(final ILaunch[] launches)
	{
		this.printHeader("Launches added");
		this.printLaunches(launches);
	}

	public final void launchesChanged(final ILaunch[] launches)
	{
		this.printHeader("Launches added");
		this.printLaunches(launches);
	}

	public final void launchesTerminated(final ILaunch[] launches)
	{
		this.printHeader("Launches added");
		this.printLaunches(launches);
	}
	
	private final void printLaunches(final ILaunch[] launches)
	{
		try
		{
			this.put(Debug.dumpLaunches(launches));
		}
		catch (CoreException e)
		{
			error("Launch error", e);
		}
	}
}
