package sk.stuba.fiit.perconik.eclipse.ui.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoleFactory;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;

public class UserInterfacePlugin extends AbstractUIPlugin
{
	/**
	 * The plug-in console. 
	 */
	protected final PluginConsole console;
	
	public UserInterfacePlugin()
	{
		this.console = PluginConsoles.create(this);
	}

	public UserInterfacePlugin(final PluginConsoleFactory factory)
	{
		this.console = factory.create(this);
	}
	
	public final PluginConsole getConsole()
	{
		return this.console;
	}
}
