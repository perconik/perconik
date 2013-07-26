package sk.stuba.fiit.perconik.debug.listeners;

import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import com.google.common.collect.ImmutableList;
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
		
		String  mode = launch.getLaunchMode();
		
		builder.append("mode: ").appendln(mode);
		builder.appendln("configuration:").lines(dumpLaunchConfiguration(configuration));

		return builder.toString();
	}
	
	private final String dumpLaunchConfiguration(final ILaunchConfiguration configuration)
	{
		SmartStringBuilder builder = new SmartStringBuilder().tab();
		
		try
		{
			ILaunchConfigurationType type = configuration.getType();
			IFile file = configuration.getFile();
			
			String      name     = configuration.getName();
			String      category = configuration.getCategory();
			Set<String> modes    = configuration.getModes();
			
			String  application = configuration.getAttribute("application", "?");
			String  product     = configuration.getAttribute("product", "?");
			boolean useProduct  = configuration.getAttribute("useProduct", false);
			
			builder.append("name: ").appendln(name);
			builder.append("category: ").appendln(category);
			builder.appendln("type:").lines(dumpLaunchConfigurationType(type));
			builder.append("modes: ").list(modes.isEmpty() ? ImmutableList.of("none") : modes).appendln();
			
			if (file != null)
			{
				builder.append("full path: ").appendln(file.getFullPath());
				builder.append("location: ").appendln(file.getLocation());
				builder.append("URI: ").appendln(file.getLocationURI());
			}
			
			builder.append("application: ").appendln(application);
			builder.append("product: ").append(product).append(" (use ").append(useProduct).appendln(")");
			
//			Map<Object, Object> attributes = configuration.getAttributes();
//			
//			for (Entry<Object, Object> entry: attributes.entrySet())
//			{
//				builder.append(entry.getKey()).append(": ").appendln(entry.getValue());
//			}
		}
		catch (CoreException e)
		{
			error("Launch configuration error", e);
		}

		return builder.toString();
	}

	private final String dumpLaunchConfigurationType(final ILaunchConfigurationType type)
	{
		SmartStringBuilder builder = new SmartStringBuilder().tab();
		
		String name             = type.getName();
		String category         = type.getCategory();
		String identifier       = type.getIdentifier();
		String pluginIdentifier = type.getPluginIdentifier();
		String contributorName  = type.getContributorName();

		builder.append("name: ").appendln(name);
		builder.append("category: ").appendln(category);
		builder.append("identifier: ").appendln(identifier);
		builder.append("plugin identifier: ").appendln(pluginIdentifier);
		builder.append("contributor name: ").appendln(contributorName);
		
		return builder.toString();
	}
}
