package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.Plugin;

/**
 * The {@code PluginConsoleFactory} creates {@link PluginConsole} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface PluginConsoleFactory
{
	/**
	 * Creates a plug-in console for given plug-in.
	 */
	public PluginConsole create(Plugin plugin);
}
