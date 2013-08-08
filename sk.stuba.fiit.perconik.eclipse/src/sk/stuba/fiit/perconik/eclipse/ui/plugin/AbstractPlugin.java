package sk.stuba.fiit.perconik.eclipse.ui.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public class AbstractPlugin extends AbstractUIPlugin
{
	/**
	 * The plug-in console. 
	 */
	protected final PluginConsole console;
	
	/**
	 * The constructor.
	 */
	public AbstractPlugin()
	{
		this.console = PluginConsole.of(this);
	}
	
	public final PluginConsole getConsole()
	{
		return this.console;
	}
}
