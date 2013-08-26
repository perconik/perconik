package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.Plugin;

public abstract class ExtendedPlugin extends Plugin
{
	/**
	 * The plug-in console. 
	 */
	protected final PluginConsole console;
	
	public ExtendedPlugin()
	{
		this.console = PluginConsoles.create(this);
	}

	public ExtendedPlugin(final PluginConsoleFactory factory)
	{
		this.console = factory.create(this);
	}
	
	public final PluginConsole getConsole()
	{
		return this.console;
	}
}
