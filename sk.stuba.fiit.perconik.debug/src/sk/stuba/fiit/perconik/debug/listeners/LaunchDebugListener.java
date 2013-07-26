package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.core.utilities.SmartStringBuilder;

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
		this.print("Launch added:");
		this.printLaunch(launch);
	}

	public final void launchRemoved(final ILaunch launch)
	{
		this.print("Launch removed:");
		this.printLaunch(launch);
	}

	public final void launchChanged(final ILaunch launch)
	{
		this.print("Launch changed:");
		this.printLaunch(launch);
	}
	
	private final void printLaunch(final ILaunch launch)
	{
		try
		{
			this.put(dumpLaunch(launch));
		}
		catch (CoreException e)
		{
			error("Launch error", e);
		}
	}
	
	static final String dumpLaunch(final ILaunch launch) throws CoreException
	{
		SmartStringBuilder builder = new SmartStringBuilder().tab();
		
		ILaunchConfiguration configuration = launch.getLaunchConfiguration();
		
		String  mode = launch.getLaunchMode();
		
		builder.append("mode: ").appendln(mode);
		builder.appendln("configuration:").lines(LaunchConfigurationDebugListener.dumpLaunchConfiguration(configuration));

		return builder.toString();
	}
}
