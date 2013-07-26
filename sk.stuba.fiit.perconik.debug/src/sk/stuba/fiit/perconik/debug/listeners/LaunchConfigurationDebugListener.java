package sk.stuba.fiit.perconik.debug.listeners;

import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import sk.stuba.fiit.perconik.core.listeners.LaunchConfigurationListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.core.utilities.SmartStringBuilder;
import com.google.common.collect.ImmutableList;

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
		this.print("Launch added:");
		this.printLaunchConfiguration(configuration);
	}

	public final void launchConfigurationRemoved(final ILaunchConfiguration configuration)
	{
		this.print("Launch removed:");
		this.printLaunchConfiguration(configuration);
	}

	public final void launchConfigurationChanged(final ILaunchConfiguration configuration)
	{
		this.print("Launch changed:");
		this.printLaunchConfiguration(configuration);
	}
	
	private final void printLaunchConfiguration(final ILaunchConfiguration configuration)
	{
		try
		{
			this.put(dumpLaunchConfiguration(configuration));
		}
		catch (CoreException e)
		{
			error("Launch configuration error", e);
		}
	}
	
	static final String dumpLaunchConfiguration(final ILaunchConfiguration configuration) throws CoreException
	{
		SmartStringBuilder builder = new SmartStringBuilder().tab();
		
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
		
//		Map<Object, Object> attributes = configuration.getAttributes();
//		
//		for (Entry<Object, Object> entry: attributes.entrySet())
//		{
//			builder.append(entry.getKey()).append(": ").appendln(entry.getValue());
//		}

		return builder.toString();
	}

	static final String dumpLaunchConfigurationType(final ILaunchConfigurationType type)
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
