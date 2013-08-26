package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.Plugin;

public interface PluginConsoleFactory
{
	public PluginConsole create(Plugin plugin);
}
