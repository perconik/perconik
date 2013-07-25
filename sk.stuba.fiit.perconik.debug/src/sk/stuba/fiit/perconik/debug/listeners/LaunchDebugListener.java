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
		this.put(dumpLaunch(launch));
	}
	
	private final String dumpLaunch(final ILaunch launch)
	{
		SmartStringBuilder builder = new SmartStringBuilder().tab();
		
		ILaunchConfiguration configuration = launch.getLaunchConfiguration();
		
		try
		{
			String  mode        = launch.getLaunchMode();
			String  name        = configuration.getName();
			String  application = configuration.getAttribute("application", "");
			String  product     = configuration.getAttribute("product", "");
			boolean useProduct  = configuration.getAttribute("useProduct", false);
			
			builder.append("mode: ").appendln(mode);
			builder.append("name: ").appendln(name);
			builder.append("application: ").appendln(application);
			builder.append("product: ").append(product).append(" use (").append(useProduct).appendln(")");
		}
		catch (CoreException e)
		{
			error("Launch configuration error", e);
		}
		
		return builder.toString();
	}
}
